package com.itravel.platform.modules.identity.api.controller;
import com.itravel.platform.common.dto.ApiResponse;
import com.itravel.platform.modules.identity.api.dto.request.LoginRequest;
import com.itravel.platform.modules.identity.api.dto.request.LogoutRequest;
import com.itravel.platform.modules.identity.api.dto.request.RefreshRequest;
import com.itravel.platform.modules.identity.api.dto.response.AuthTokenResponse;
import com.itravel.platform.modules.identity.api.mapper.AuthRestMapper;
import com.itravel.platform.modules.identity.application.command.auth.LoginCommand;
import com.itravel.platform.modules.identity.application.command.auth.LogoutCommand;
import com.itravel.platform.modules.identity.application.command.auth.RefreshCommand;
import com.itravel.platform.modules.identity.application.handler.AuthCommandHandler;
import com.nimbusds.jose.JOSEException;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/auth")
public class AuthController {
    AuthRestMapper mapper;
    AuthCommandHandler authCommandHandler;

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
}
