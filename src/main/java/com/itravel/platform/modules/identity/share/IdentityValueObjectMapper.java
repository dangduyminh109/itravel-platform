package com.itravel.platform.modules.identity.share;

import com.itravel.platform.modules.identity.application.command.account.PermissionOverrideCommand;
import com.itravel.platform.modules.identity.api.dto.request.PermissionOverrideRequest;
import com.itravel.platform.modules.identity.domain.aggregate.Role;
import com.itravel.platform.modules.identity.domain.aggregate.enums.PermissionType;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.*;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface IdentityValueObjectMapper {
    default UserId toUserId(String id) {
        return id != null ? new UserId(id) : null;
    }
    default String fromUserId(UserId id) {
        return id != null ? id.value() : null;
    }

    default AccountId toAccountId(String id) {
        return id != null ? new AccountId(id) : null;
    }
    default String fromAccountId(AccountId id) {
        return id != null ? id.value() : null;
    }

    default CustomerId toCustomerId(String id) {
        return id != null ? new CustomerId(id) : null;
    }
    default String fromCustomerId(CustomerId id) {
        return id != null ? id.value() : null;
    }

    default Email toEmail(String email) {
        return email != null ? new Email(email) : null;
    }
    default String fromEmail(Email email) {
        return email != null ? email.value() : null;
    }

    default Username toUsername(String username) {
        return username != null ? new Username(username) : null;
    }
    default String fromUsername(Username username) {
        return username != null ? username.value() : null;
    }

    default FullName toFullName(String fullName) {
        return fullName != null ? new FullName(fullName) : null;
    }
    default String fromFullName(FullName fullName) {
        return fullName != null ? fullName.value() : null;
    }

    default PasswordHash toPasswordHash(String password) {
        return password != null ? new PasswordHash(password) : null;
    }

    default String fromPasswordHash(PasswordHash passwordHash) {
        return passwordHash != null ? passwordHash.value() : null;
    }

    default RawPassword toRawPassword(String password) {
        return password != null && !password.isBlank() ? new RawPassword(password) : null;
    }

    default String fromRawPassword(RawPassword rawPassword) {
        return rawPassword != null ? rawPassword.value() : null;
    }

    default RoleId toRoleId(Long id) {
        return id != null ? new RoleId(id) : null;
    }
    default Long fromRoleId(RoleId id) {
        return id != null ? id.value() : null;
    }

    default RoleName toRoleName(String name) {
        return name != null ? new RoleName(name) : null;
    }

    default String fromRoleName(RoleName name) {
        return name != null ? name.value() : null;
    }

    default Long fromRole(Role role) {
        return role != null ? role.getId().value() : null;
    }

    default OtpCode toOtpCode(String otp) {
        return otp != null ? new OtpCode(otp) : null;
    }

    default String fromOtpCode(OtpCode otp) {
        return otp != null ? otp.value() : null;
    }

    default Permission toPermission(String code) {
        return code != null ? new Permission(code) : null;
    }
    default String fromPermission(Permission permission) {
        return permission != null ? permission.code() : null;
    }

    default TokenId toTokenId(String id) {
        return id != null ? new TokenId(id) : null;
    }
    default String fromTokenId(TokenId id) {
        return id != null ? id.value() : null;
    }

    default TokenHash toTokenHash(String hash) {
        return hash != null ? new TokenHash(hash) : null;
    }
    default String fromTokenHash(TokenHash hash) {
        return hash != null ? hash.value() : null;
    }

    default PermissionType toPermissionType(String type) {
        return type != null && !type.isBlank() ? PermissionType.valueOf(type) : null;
    }
    default String fromPermissionType(PermissionType type) {
        return type != null ? type.name() : null;
    }

    default PermissionOverrideCommand toPermissionOverrideCommand(PermissionOverrideRequest request) {
        return request != null
                ? new PermissionOverrideCommand(
                toPermission(request.permission()),
                toPermissionType(request.permissionType())
        )
                : null;
    }
    default PermissionOverrideRequest fromPermissionOverrideCommand(PermissionOverrideCommand command) {
        return command != null
                ? new PermissionOverrideRequest(
                fromPermission(command.permission()),
                fromPermissionType(command.permissionType())
        )
                : null;
    }
}