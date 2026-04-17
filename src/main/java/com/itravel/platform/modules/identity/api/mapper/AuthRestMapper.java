package com.itravel.platform.modules.identity.api.mapper;

import com.itravel.platform.modules.identity.api.dto.request.*;
import com.itravel.platform.modules.identity.api.dto.response.AuthTokenResponse;
import com.itravel.platform.modules.identity.application.command.model.auth.*;
import com.itravel.platform.modules.identity.application.dto.AuthTokenDTO;
import com.itravel.platform.modules.identity.share.IdentityValueObjectMapper;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        uses = IdentityValueObjectMapper.class,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface AuthRestMapper {
    AuthTokenResponse toAuthTokenResponse(AuthTokenDTO AuthTokenDTO);
    FirebaseLoginCommand toFirebaseLoginCommand(FirebaseLoginRequest request);
    LoginCommand toLoginCommand(LoginRequest request);
    LogoutCommand toLogoutCommand(LogoutRequest request);
    RefreshCommand toRefreshCommand(RefreshRequest request);
    CustomerForgotPasswordCommand toCustomerForgotPasswordCommand(CustomerForgotPasswordRequest request);
}