package com.itravel.platform.modules.identity.application.port.in.auth.facade;

import com.itravel.platform.modules.identity.application.command.model.auth.CustomerForgotPasswordCommand;
import com.itravel.platform.modules.identity.application.command.model.auth.FirebaseLoginCommand;
import com.itravel.platform.modules.identity.application.command.model.auth.LoginCommand;
import com.itravel.platform.modules.identity.application.command.model.auth.LogoutCommand;
import com.itravel.platform.modules.identity.application.command.model.auth.RefreshCommand;
import com.itravel.platform.modules.identity.application.command.model.auth.RegisterCustomerByEmailCommand;
import com.itravel.platform.modules.identity.application.command.model.auth.SendOtpCommand;
import com.itravel.platform.modules.identity.application.command.model.customer.RegisterCustomerByGoogleCommand;
import com.itravel.platform.modules.identity.application.port.in.auth.CustomerForgotPasswordUseCase;
import com.itravel.platform.modules.identity.application.port.in.auth.FirebaseLoginUseCase;
import com.itravel.platform.modules.identity.application.port.in.auth.LoginUseCase;
import com.itravel.platform.modules.identity.application.port.in.auth.LogoutUseCase;
import com.itravel.platform.modules.identity.application.port.in.auth.RefreshUseCase;
import com.itravel.platform.modules.identity.application.port.in.auth.RegisterCustomerByEmailUseCase;
import com.itravel.platform.modules.identity.application.port.in.auth.RegisterCustomerByGoogleUseCase;
import com.itravel.platform.modules.identity.application.port.in.auth.SendOtpUseCase;
import com.itravel.platform.modules.identity.domain.account.Account;
import com.itravel.platform.modules.identity.application.dto.AuthTokenDTO;
import com.itravel.platform.modules.identity.application.dto.CustomerDetailDTO;
import com.nimbusds.jose.JOSEException;
import jakarta.mail.MessagingException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthCommandFacade {
    FirebaseLoginUseCase firebaseLoginUseCase;
    LoginUseCase loginUseCase;
    LogoutUseCase logoutUseCase;
    RefreshUseCase refreshUseCase;
    RegisterCustomerByEmailUseCase registerCustomerByEmailUseCase;
    RegisterCustomerByGoogleUseCase registerCustomerByGoogleUseCase;
    CustomerForgotPasswordUseCase customerForgotPasswordUseCase;
    SendOtpUseCase sendOtpUseCase;

    public AuthTokenDTO authenticateWithFirebase(FirebaseLoginCommand command) {
        return firebaseLoginUseCase.execute(command);
    }

    public AuthTokenDTO login(LoginCommand command) throws JOSEException {
        return loginUseCase.execute(command);
    }

    public void logout(LogoutCommand command) {
        logoutUseCase.execute(command);
    }

    public AuthTokenDTO refresh(RefreshCommand command) throws JOSEException {
        return refreshUseCase.execute(command);
    }

    public CustomerDetailDTO registerCustomerByEmail(RegisterCustomerByEmailCommand command) {
        return registerCustomerByEmailUseCase.execute(command);
    }

    public Account registerCustomerByGoogle(RegisterCustomerByGoogleCommand command) {
        return registerCustomerByGoogleUseCase.execute(command);
    }

    public String forgotPassword(CustomerForgotPasswordCommand command) {
        return customerForgotPasswordUseCase.execute(command);
    }

    public String sendOtp(SendOtpCommand command) throws MessagingException {
        return sendOtpUseCase.execute(command);
    }
}
