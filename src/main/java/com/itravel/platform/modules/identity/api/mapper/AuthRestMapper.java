package com.itravel.platform.modules.identity.api.mapper;

import com.itravel.platform.modules.identity.api.dto.request.LoginRequest;
import com.itravel.platform.modules.identity.api.dto.response.AuthTokenResponse;
import com.itravel.platform.modules.identity.application.command.auth.LoginCommand;
import com.itravel.platform.modules.identity.application.query.AuthToken;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AuthRestMapper {
    AuthTokenResponse toAuthTokenResponse(AuthToken authToken);

    @Mapping(target = "password",
            expression = "java(request.password() != null ? new PasswordHash(request.password()) : null)")
    LoginCommand toLoginCommand(LoginRequest request);

//    @Mapping(target = "accountId",
//            expression = "java(request.id() != null ? new AccountId(request.id()) : null)")
//    SystemLogoutCommand toSystemLogoutCommand(SystemLogoutRequest request);
//
//    SystemRefreshCommand toSystemRefreshCommand(SystemRefreshRequest request);
}
