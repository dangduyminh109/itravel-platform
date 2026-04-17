package com.itravel.platform.modules.identity.api.controller;

import com.itravel.platform.common.dto.ApiResponse;
import com.itravel.platform.modules.identity.api.dto.request.FirebaseLoginRequest;
import com.itravel.platform.modules.identity.api.dto.request.LoginRequest;
import com.itravel.platform.modules.identity.api.dto.request.LogoutRequest;
import com.itravel.platform.modules.identity.api.dto.request.RefreshRequest;
import com.itravel.platform.modules.identity.api.dto.request.RegisterCustomerByEmailRequest;
import com.itravel.platform.modules.identity.api.dto.request.CustomerForgotPasswordRequest;
import com.itravel.platform.modules.identity.api.dto.request.SendOtpRequest;
import com.itravel.platform.modules.identity.api.dto.response.AuthTokenResponse;
import com.itravel.platform.modules.identity.api.dto.response.CustomerResponse;
import com.itravel.platform.modules.identity.api.mapper.AuthRestMapper;
import com.itravel.platform.modules.identity.api.mapper.CustomerRestMapper;
import com.itravel.platform.modules.identity.api.mapper.OtpRestMapper;
import com.itravel.platform.modules.identity.application.command.model.auth.FirebaseLoginCommand;
import com.itravel.platform.modules.identity.application.command.model.auth.LoginCommand;
import com.itravel.platform.modules.identity.application.command.model.auth.LogoutCommand;
import com.itravel.platform.modules.identity.application.command.model.auth.RefreshCommand;
import com.itravel.platform.modules.identity.application.command.model.auth.RegisterCustomerByEmailCommand;
import com.itravel.platform.modules.identity.application.command.model.auth.CustomerForgotPasswordCommand;
import com.itravel.platform.modules.identity.application.command.model.auth.SendOtpCommand;
import com.itravel.platform.modules.identity.application.port.in.auth.facade.AuthCommandFacade;
import com.nimbusds.jose.JOSEException;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/auth")
public class AuthController {
    AuthRestMapper mapper;
    AuthCommandFacade authCommandFacade;
    OtpRestMapper otpMapper;
    CustomerRestMapper customerMapper;

    @PostMapping("/firebase-login")
    ApiResponse<AuthTokenResponse> firebaseLogin(@RequestBody @Valid FirebaseLoginRequest request) {
        FirebaseLoginCommand command = mapper.toFirebaseLoginCommand(request);
        AuthTokenResponse authTokenResponse = mapper
                .toAuthTokenResponse(authCommandFacade.authenticateWithFirebase(command));

        return ApiResponse.<AuthTokenResponse>builder()
                .success(true)
                .message("login successfully")
                .response(authTokenResponse)
                .build();
    }

    @PostMapping("/login")
    ApiResponse<AuthTokenResponse> login(@RequestBody @Valid LoginRequest request) throws JOSEException {
        LoginCommand loginCommand = mapper.toLoginCommand(request);

        AuthTokenResponse authTokenResponse = mapper
                .toAuthTokenResponse(authCommandFacade.login(loginCommand));

        return ApiResponse.<AuthTokenResponse>builder()
                .success(true)
                .message("login successfully")
                .response(authTokenResponse)
                .build();
    }

    @PostMapping("/logout")
    ApiResponse<Void> logout(@RequestBody @Valid LogoutRequest request) {
        LogoutCommand logoutCommand = mapper.toLogoutCommand(request);
        authCommandFacade.logout(logoutCommand);
        return ApiResponse.<Void>builder()
                .success(true)
                .message("logout successfully")
                .build();
    }

    @PostMapping("/refresh")
    ApiResponse<AuthTokenResponse> refresh(@RequestBody @Valid RefreshRequest request) throws JOSEException {
        RefreshCommand refreshCommand = mapper.toRefreshCommand(request);

        AuthTokenResponse authTokenResponse = mapper
                .toAuthTokenResponse(authCommandFacade.refresh(refreshCommand));

        return ApiResponse.<AuthTokenResponse>builder()
                .response(authTokenResponse)
                .success(true)
                .message("refresh successfully")
                .build();
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<CustomerResponse> register(@RequestBody @Valid RegisterCustomerByEmailRequest request) {
        RegisterCustomerByEmailCommand command = customerMapper.toCreateCustomerByEmailCommand(request);
        return ApiResponse.<CustomerResponse>builder()
                .message("Create customer successfully")
                .success(true)
                .response(customerMapper
                        .toCustomerResponse(authCommandFacade.registerCustomerByEmail(command)))
                .build();
    }

    @PostMapping("/forgot-password")
    ApiResponse<String> forgotPassword(@RequestBody @Valid CustomerForgotPasswordRequest request) {
        CustomerForgotPasswordCommand command =
                mapper.toCustomerForgotPasswordCommand(request);
        return ApiResponse.<String>builder()
                .message(authCommandFacade.forgotPassword(command))
                .success(true)
                .response(null)
                .build();
    }

    @PostMapping("/send-otp")
    ApiResponse<Void> sendOtp(@RequestBody @Valid SendOtpRequest request) throws MessagingException {
        SendOtpCommand command = otpMapper.toSendOtpCommand(request);
        return ApiResponse.<Void>builder()
                .message(authCommandFacade.sendOtp(command))
                .success(true)
                .response(null)
                .build();
    }
}
