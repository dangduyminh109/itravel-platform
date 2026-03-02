package com.itravel.platform.modules.identity.application.handler;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;
import com.itravel.platform.modules.identity.application.command.account.CreateAccountByEmailCommand;
import com.itravel.platform.modules.identity.application.command.account.CreateAccountByGoogleCommand;
import com.itravel.platform.modules.identity.application.command.auth.*;
import com.itravel.platform.modules.identity.application.command.customer.RegisterCustomerByGoogleCommand;
import com.itravel.platform.modules.identity.application.exception.*;
import com.itravel.platform.modules.identity.application.query.AuthToken;
import com.itravel.platform.modules.identity.application.query.CustomerDetail;
import com.itravel.platform.modules.identity.application.service.EmailService;
import com.itravel.platform.modules.identity.application.service.TokenApplicationService;
import com.itravel.platform.modules.identity.domain.aggregate.Account;
import com.itravel.platform.modules.identity.domain.aggregate.Customer;
import com.itravel.platform.modules.identity.domain.aggregate.Otp;
import com.itravel.platform.modules.identity.domain.aggregate.RefreshToken;
import com.itravel.platform.modules.identity.domain.aggregate.enums.AccountStatus;
import com.itravel.platform.modules.identity.domain.aggregate.enums.AuthProvider;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.*;
import com.itravel.platform.modules.identity.domain.exception.InvalidOtpCodeException;
import com.itravel.platform.modules.identity.domain.repository.AccountRepository;
import com.itravel.platform.modules.identity.domain.repository.CustomerRepository;
import com.itravel.platform.modules.identity.domain.repository.OtpRepository;
import com.itravel.platform.modules.identity.domain.repository.RefreshTokenRepository;
import com.itravel.platform.modules.identity.domain.service.RefreshTokenHasher;
import com.itravel.platform.modules.identity.infrastructure.security.PasswordEncoderAdapter;
import com.nimbusds.jose.JOSEException;
import jakarta.mail.MessagingException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.time.Duration;
import java.time.Instant;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthCommandHandler {
    AccountRepository accountRepository;
    RefreshTokenRepository refreshTokenRepository;
    RefreshTokenHasher refreshTokenHasher;
    OtpRepository otpRepository;
    CustomerRepository customerRepository;
    PasswordEncoderAdapter passwordEncoderAdapter;
    EmailService emailService;
    AccountCommandHandler accountCommandHandler;
    TokenApplicationService tokenApplicationService;

    @NonFinal
    @Value("${security.otp.otp-duration}")
    long VALID_DURATION_OTP;

    @Transactional
    public AuthToken login(LoginCommand command) throws JOSEException {
        Account account = accountRepository
                .findByIdentifier(command.identifier())
                .orElseThrow(AccountNotExistException::new);

        // check valid account
        if (AccountStatus.INACTIVE.equals(account.getStatus())) {
            throw new AccountInActiveException();
        }

        if (!Objects.isNull(account.getDeletedAt())) {
            throw new AccountDeletedException();
        }

        if (AuthProvider.USERNAME.equals(account.getAuthProvider()) || AuthProvider.EMAIL.equals(account.getAuthProvider())) {
            if(!passwordEncoderAdapter.matches(command.password(), account.getPassword())){
                if(account.getAuthProvider().equals(AuthProvider.USERNAME)){
                    throw new UsernameOrPasswordInvalidException();
                } else{
                    throw new EmailOrPasswordInvalidException();
                }
            }
        } else {
            throw new InvalidLoginMethodException();
        }

        return tokenApplicationService.createToken(account);
    }

    @Transactional
    public AuthToken authenticateWithFirebase(FirebaseLoginCommand command){
        try {
            FirebaseToken decodedToken =
                    FirebaseAuth.getInstance().verifyIdToken(command.idToken());

            String email = decodedToken.getEmail();
            String uid = decodedToken.getUid();
            String name = decodedToken.getName();

            Account account = accountRepository.findByEmail(new Email(email))
                    .orElseGet(() -> {
                        RegisterCustomerByGoogleCommand registerCommand = new RegisterCustomerByGoogleCommand(
                                new Email(email),
                                new FullName(name),
                                new ProviderId(uid)
                        );
                        return RegisterCustomerByGoogle(registerCommand);
                    });

            return tokenApplicationService
                    .createToken(account);
        } catch (Exception e) {
            throw new InvalidFirebaseTokenException();
        }
    }

    @Transactional
    public void logout(LogoutCommand command){
        String refreshTokenRaw = command.refreshToken();
        TokenHash refreshTokenHash = refreshTokenHasher.hash(refreshTokenRaw);

        Optional<RefreshToken> refreshTokenOpt = refreshTokenRepository
                .findByTokenHash(refreshTokenHash);

        if(refreshTokenOpt.isEmpty()) {
            return;
        }

        RefreshToken refreshToken = refreshTokenOpt.get();

        refreshToken.revoke();

        refreshTokenRepository.save(refreshToken);
    }

    @Transactional
    public AuthToken refresh(RefreshCommand command) throws JOSEException {
        // revoke old token
        String refreshTokenRaw = command.refreshToken();
        TokenHash refreshTokenHash = refreshTokenHasher.hash(refreshTokenRaw);

        RefreshToken refreshToken = refreshTokenRepository.findByTokenHash(refreshTokenHash)
                .orElseThrow(RefreshTokenNotFoundException::new);

        if(refreshToken.isRevoked()){
            throw new RefreshTokenRevokedException();
        }
        if (refreshToken.isExpired()) {
            throw new RefreshTokenExpiredException();
        }

        refreshToken.revoke();
        refreshTokenRepository.save(refreshToken);

        // create new token
        Account account = accountRepository.findById(refreshToken.getAccountId())
                .orElseThrow(AccountNotExistException::new);

        // check valid account
        if (AccountStatus.INACTIVE.equals(account.getStatus())) {
            throw new AccountInActiveException();
        }

        if (!Objects.isNull(account.getDeletedAt())) {
            throw new AccountDeletedException();
        }

        return tokenApplicationService.createToken(account);
    }

    @Transactional
    public CustomerDetail RegisterCustomerByEmail(RegisterCustomerByEmailCommand command) {
        Otp otp = otpRepository.findByEmailAndCode(command.email(), command.otp())
                .orElseThrow(InvalidOtpCodeException::new);
        otp.verify(command.otp());

        Customer customer = Customer.create(
                command.fullName(),
                null,
                null,
                null,
                null,
                null,
                null,
                null
        );
        CreateAccountByEmailCommand createAccountByEmailCommand
                = new CreateAccountByEmailCommand(
                command.email(),
                command.password(),
                customer.getId()
        );
        Account account = accountCommandHandler.CreateByEmail(createAccountByEmailCommand);
        customerRepository.save(customer);
        otpRepository.destroy(otp.getId());
        return CustomerDetail.builder()
                .id(customer.getId())
                .fullName(customer.getFullName())
                .phoneNumber(customer.getPhoneNumber())
                .avatar(customer.getAvatar())
                .email(account.getEmail())
                .roleList(account.getRoleList())
                .createdAt(customer.getCreatedAt())
                .updatedAt(customer.getUpdatedAt())
                .deletedAt(customer.getDeletedAt())
                .build();
    }

    @Transactional
    public Account RegisterCustomerByGoogle(RegisterCustomerByGoogleCommand command) {
        Customer customer = Customer.create(
                command.fullName(),
                null,
                null,
                null,
                null,
                null,
                null,
                null
        );

        CreateAccountByGoogleCommand createAccountByGoogleCommand =
                new CreateAccountByGoogleCommand(
                        command.email(),
                        customer.getId(),
                        command.providerId()
                );
        Account account = accountCommandHandler.CreateByGoogle(createAccountByGoogleCommand);
        customerRepository.save(customer);
        return account;
    }

    @Transactional
    public String forgotPassword(CustomerForgotPasswordCommand command) {
        Otp otp = otpRepository.findByEmailAndCode(command.email(), command.otp())
                .orElseThrow(InvalidOtpCodeException::new);
        otp.verify(command.otp());

        if (!command.newPassword().equals(command.confirmPassword()))
            throw new PasswordConfirmationMismatchException();

        Account account = accountRepository.findByEmail(command.email())
                        .orElseThrow(AccountNotExistException::new);

        account.changePassword(passwordEncoderAdapter.encode(command.newPassword()));
        accountRepository.save(account);
        otpRepository.destroy(otp.getId());
        return "Đổi mật khẩu thành công";
    }

    @Transactional
    public String sendOtp(SendOtpCommand command) throws MessagingException {
        Optional<Otp> existingOtp = otpRepository.findFirstByEmailAndExpiresAtAfter(
                command.email(), Instant.now());

        if (existingOtp.isPresent()) {
            return "The email has been sent.";
        }

        Otp otp = Otp.create(
                command.email(),
                new OtpCode(generateOtpCode()),
                Duration.ofMinutes(VALID_DURATION_OTP)
        );

        Otp result = otpRepository.save(otp);
        emailService.sendEmail(command.email().value(), result.getCode().value());
        return "Email đã được gửi";
    }

    public String generateOtpCode() {
        Random random = new SecureRandom();
        int otp = 100000 + random.nextInt(900000);
        return String.valueOf(otp);
    }
}