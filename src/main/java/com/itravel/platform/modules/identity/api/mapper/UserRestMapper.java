package com.itravel.platform.modules.identity.api.mapper;

import com.itravel.platform.modules.identity.api.dto.request.CreateUserRequest;
import com.itravel.platform.modules.identity.api.dto.request.UpdateUserRequest;
import com.itravel.platform.modules.identity.api.dto.response.UserResponse;
import com.itravel.platform.modules.identity.application.command.user.*;
import com.itravel.platform.modules.identity.application.query.UserDetail;
import com.itravel.platform.modules.identity.share.IdentityValueObjectMapper;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        uses = {
                RoleRestMapper.class,
                IdentityValueObjectMapper.class
        },
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface UserRestMapper {
    UserResponse toUserResponse(UserDetail user);
    CreateUserCommand toCreateUserCommand(CreateUserRequest request);
    UpdateUserCommand toUpdateUserCommand(String id, UpdateUserRequest request);
    DeleteUserCommand toDeleteUserCommand(String id);
    RestoreUserCommand toRestoreUserCommand(String id);
}