package com.itravel.platform.modules.identity.api.controller;
import com.itravel.platform.common.dto.ApiResponse;
import com.itravel.platform.modules.identity.api.dto.request.LoginRequest;
import com.itravel.platform.modules.identity.api.dto.response.AuthTokenResponse;
import com.itravel.platform.modules.identity.api.mapper.AuthRestMapper;
import com.itravel.platform.modules.identity.application.command.auth.LoginCommand;
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
}
