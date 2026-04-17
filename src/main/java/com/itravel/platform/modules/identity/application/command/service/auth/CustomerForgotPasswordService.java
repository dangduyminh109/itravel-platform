package com.itravel.platform.modules.identity.application.command.service.auth;

import com.itravel.platform.modules.identity.application.command.model.auth.CustomerForgotPasswordCommand;
import com.itravel.platform.modules.identity.application.exception.AccountNotExistException;
import com.itravel.platform.modules.identity.application.exception.PasswordConfirmationMismatchException;
import com.itravel.platform.modules.identity.application.port.in.auth.CustomerForgotPasswordUseCase;
import com.itravel.platform.modules.identity.application.port.out.account.AccountRepository;
import com.itravel.platform.modules.identity.application.port.out.otp.OtpRepository;
import com.itravel.platform.modules.identity.domain.account.Account;
import com.itravel.platform.modules.identity.domain.otp.Otp;
import com.itravel.platform.modules.identity.domain.otp.exception.InvalidOtpCodeException;
import com.itravel.platform.modules.identity.infrastructure.security.PasswordEncoderAdapter;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CustomerForgotPasswordService implements CustomerForgotPasswordUseCase {
    OtpRepository otpRepository;
    AccountRepository accountRepository;
    PasswordEncoderAdapter passwordEncoderAdapter;

    @Override
    @Transactional
    public String execute(CustomerForgotPasswordCommand command) {
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
}
