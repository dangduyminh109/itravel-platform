package com.itravel.platform.modules.identity.api.mapper;

import com.itravel.platform.modules.identity.api.dto.request.CreateUserRequest;
import com.itravel.platform.modules.identity.api.dto.response.UserResponse;
import com.itravel.platform.modules.identity.application.command.user.*;
import com.itravel.platform.modules.identity.application.query.UserDetail;
import com.itravel.platform.modules.identity.api.dto.request.UpdateUserRequest;
import com.itravel.platform.modules.identity.domain.aggregate.Role;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.RoleId;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",uses = RoleRestMapper.class, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserRestMapper {
    @Mapping(target = "id",
            expression = "java(user.id().value() != null ? user.id().value() : null)")
    @Mapping(target = "fullName",
            expression = "java(user.fullName().value() != null ? user.fullName().value() : null)")
    @Mapping(target = "username",
            expression = "java(user.username().value() != null ? user.username().value() : null)")
    UserResponse toUserResponse(UserDetail user);

    @Mapping(target = "username",
            expression = "java(request.username() != null ? new Username(request.username()) : null)")
    @Mapping(target = "password",
            expression = "java(request.password() != null ? new PasswordHash(request.password()) : null)")
    @Mapping(target = "fullName",
            expression = "java(request.fullName() != null ? new FullName(request.fullName()) : null)")
    CreateUserCommand toCreateUserCommand(CreateUserRequest request);

    @Mapping(target = "userId",
            expression = "java(userId != null ? new UserId(userId) : null)")
    @Mapping(target = "password",
            expression = "java(request.password() != null ? new PasswordHash(request.password()) : null)")
    @Mapping(target = "fullName",
            expression = "java(request.fullName() != null ? new FullName(request.fullName()) : null)")
    UpdateUserCommand toUpdateUserCommand(String userId, UpdateUserRequest request);

    @Mapping(target = "userId",
            expression = "java(userId != null ? new UserId(userId) : null)")
    DeleteUserCommand toDeleteUserCommand(String userId);

    @Mapping(target = "userId",
            expression = "java(userId != null ? new UserId(userId) : null)")
    RestoreUserCommand toRestoreUserCommand(String userId);

    default RoleId map(Long roleId) {
        return roleId != null ? new RoleId(roleId) : null;
    }

    default Long map(Role role) {
        return role.getId().value();
    }
}