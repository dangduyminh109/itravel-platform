package com.itravel.platform.modules.identity.api.controller;
import com.itravel.platform.common.dto.ApiResponse;
import com.itravel.platform.modules.identity.api.dto.request.*;
import com.itravel.platform.modules.identity.api.dto.response.AuthTokenResponse;
import com.itravel.platform.modules.identity.api.dto.response.CustomerResponse;
import com.itravel.platform.modules.identity.api.mapper.AuthRestMapper;
import com.itravel.platform.modules.identity.api.mapper.CustomerRestMapper;
import com.itravel.platform.modules.identity.api.mapper.OtpRestMapper;
import com.itravel.platform.modules.identity.application.command.auth.*;
import com.itravel.platform.modules.identity.application.handler.AuthCommandHandler;
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
    AuthCommandHandler authCommandHandler;
    OtpRestMapper otpMapper;
    CustomerRestMapper customerMapper;

    @PostMapping("/login")
    ApiResponse<AuthTokenResponse> login(@RequestBody @Valid LoginRequest request) throws JOSEException {
        LoginCommand loginCommand = mapper.toLoginCommand(request);

        AuthTokenResponse authTokenResponse = mapper
                .toAuthTokenResponse(authCommandHandler.login(loginCommand));

        return ApiResponse.<AuthTokenResponse>builder()
                .message("login successfully")
                .data(authTokenResponse)
                .build();
    }

    @PostMapping("/logout")
    ApiResponse<Void> logout(@RequestBody @Valid LogoutRequest request) {
        LogoutCommand LogoutCommand = mapper.toLogoutCommand(request);
        authCommandHandler.logout(LogoutCommand);
        return ApiResponse.<Void>builder()
                .message("logout successfully")
                .build();
    }

    @PostMapping("/refresh")
    ApiResponse<AuthTokenResponse> refresh(@RequestBody @Valid RefreshRequest request) throws JOSEException {
        RefreshCommand RefreshCommand = mapper.toRefreshCommand(request);

        AuthTokenResponse authTokenResponse = mapper
                .toAuthTokenResponse(authCommandHandler.refresh(RefreshCommand));

        return ApiResponse.<AuthTokenResponse>builder()
                .data(authTokenResponse)
                .message("logout successfully")
                .build();
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<CustomerResponse> register(@RequestBody RegisterCustomerByEmailRequest request) {
        RegisterCustomerByEmailCommand command = customerMapper.toCreateCustomerByEmailCommand(request);
        return ApiResponse.<CustomerResponse>builder()
                .message("Create customer successfully")
                .success(true)
                .data(customerMapper
                        .toCustomerResponse(authCommandHandler
                                .RegisterCustomerByEmail(command)))
                .build();
    }

    @PostMapping("/forgot-password")
    ApiResponse<String> forgotPassword(@RequestBody CustomerForgotPasswordRequest request) throws JOSEException {
        CustomerForgotPasswordCommand command =
                mapper.toCustomerForgotPasswordCommand(request);
        return ApiResponse.<String>builder()
                .message(authCommandHandler.forgotPassword(command))
                .success(true)
                .data(null)
                .build();
    }

    @PostMapping("/send-otp")
    ApiResponse<Void> sendOtp(@RequestBody SendOtpRequest request) throws MessagingException {
        SendOtpCommand command = otpMapper.toSendOtpCommand(request);
        return ApiResponse.<Void>builder()
                .message(authCommandHandler.sendOtp(command))
                .success(true)
                .data(null)
                .build();
    }
}
