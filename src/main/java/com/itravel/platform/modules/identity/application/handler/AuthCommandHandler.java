package com.itravel.platform.modules.identity.application.handler;

import com.itravel.platform.modules.identity.application.command.account.CreateAccountByEmailCommand;
import com.itravel.platform.modules.identity.application.command.auth.*;
import com.itravel.platform.modules.identity.application.exception.*;
import com.itravel.platform.modules.identity.application.query.AuthToken;
import com.itravel.platform.modules.identity.application.query.CustomerDetail;
import com.itravel.platform.modules.identity.application.service.EmailService;
import com.itravel.platform.modules.identity.domain.aggregate.Account;
import com.itravel.platform.modules.identity.domain.aggregate.Customer;
import com.itravel.platform.modules.identity.domain.aggregate.Otp;
import com.itravel.platform.modules.identity.domain.aggregate.RefreshToken;
import com.itravel.platform.modules.identity.domain.aggregate.enums.AccountLinkType;
import com.itravel.platform.modules.identity.domain.aggregate.enums.AccountStatus;
import com.itravel.platform.modules.identity.domain.aggregate.enums.AuthProvider;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.OtpCode;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.Permission;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.TokenHash;
import com.itravel.platform.modules.identity.domain.exception.InvalidOtpCodeException;
import com.itravel.platform.modules.identity.domain.repository.AccountRepository;
import com.itravel.platform.modules.identity.domain.repository.CustomerRepository;
import com.itravel.platform.modules.identity.domain.repository.OtpRepository;
import com.itravel.platform.modules.identity.domain.repository.RefreshTokenRepository;
import com.itravel.platform.modules.identity.domain.service.RefreshTokenGenerator;
import com.itravel.platform.modules.identity.domain.service.RefreshTokenHasher;
import com.itravel.platform.modules.identity.domain.service.TokenProvider;
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
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthCommandHandler {
    AccountRepository accountRepository;
    RefreshTokenRepository refreshTokenRepository;
    TokenProvider tokenProvider;
    RefreshTokenGenerator refreshTokenGenerator;
    RefreshTokenHasher refreshTokenHasher;
    PasswordEncoderAdapter passwordEncoderAdapter;
    OtpRepository otpRepository;
    EmailService emailService;
    AccountCommandHandler accountCommandHandler;
    CustomerRepository customerRepository;

    @NonFinal
    @Value("${security.refresh-token.refreshable-duration}")
    long REFRESH_DURATION;

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
            if(!passwordEncoderAdapter.matches(command.password().value(),account.getPassword())){
                if(account.getAuthProvider().equals(AuthProvider.USERNAME)){
                    throw new UsernameOrPasswordInvalidException();
                } else{
                    throw new EmailOrPasswordInvalidException();
                }
            }
        } else {
            throw new InvalidLoginMethodException();
        }

        return createToken(account);
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
        refreshToken.verifyOwner(command.accountId());

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

        refreshToken.verifyOwner(command.accountId());

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

        return createToken(account);
    }

    private AuthToken createToken(Account account) throws JOSEException {
        Set<Permission> permissionSet = account.getRoleList().stream()
                .flatMap(role -> role.getPermissionList().stream())
                .collect(Collectors.toSet());

        String permissionList = permissionSet.stream()
                .map(Permission::code)
                .collect(Collectors.joining(","));
        String roleList = account.getRoleList().stream()
                .map(role -> role.getName().value())
                .collect(Collectors.joining(","));

        AccountLinkType type = AuthProvider.USERNAME.equals(account.getAuthProvider())
                ? AccountLinkType.SYSTEM_USER : AccountLinkType.CUSTOMER;

        String accessToken = tokenProvider.generateAccessToken(
                account.getId(),
                type,
                roleList,permissionList
        );

        String refreshTokenRaw = refreshTokenGenerator.generate();
        TokenHash refreshTokenHash = refreshTokenHasher.hash(refreshTokenRaw);
        Duration duration = Duration.ofSeconds(REFRESH_DURATION);
        RefreshToken refreshToken = RefreshToken.create(
                account.getId(),
                refreshTokenHash,
                duration
        );
        refreshTokenRepository.save(refreshToken);
        return new AuthToken(
                accessToken,
                refreshTokenRaw,
                refreshToken.getExpiresAt()
        );
    }

    @Transactional
    public CustomerDetail RegisterCustomerByEmail(RegisterCustomerByEmailCommand command) {
        Otp otp = otpRepository.findByEmailAndCode(command.email(), command.otp())
                .orElseThrow(InvalidOtpCodeException::new);
        otp.verify(command.otp());

        Customer customer = Customer.create(command.fullName());
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
                .email(account.getEmail())
                .roleList(account.getRoleList())
                .createdAt(customer.getCreatedAt())
                .updatedAt(customer.getUpdatedAt())
                .deletedAt(customer.getDeletedAt())
                .build();
    }

    @Transactional
    public String forgotPassword(CustomerForgotPasswordCommand command) throws JOSEException {
        Otp otp = otpRepository.findByEmailAndCode(command.email(), command.otp())
                .orElseThrow(InvalidOtpCodeException::new);
        otp.verify(command.otp());

        if (!command.newPassword().equals(command.confirmPassword()))
            throw new PasswordConfirmationMismatchException();

        Account account = accountRepository.findByEmail(command.email())
                        .orElseThrow(AccountNotExistException::new);

        account.changePassword(passwordEncoderAdapter.encode(command.newPassword().value()));
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