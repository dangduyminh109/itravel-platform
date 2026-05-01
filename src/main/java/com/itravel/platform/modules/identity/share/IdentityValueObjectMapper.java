package com.itravel.platform.modules.identity.share;

import com.itravel.platform.modules.identity.application.command.model.account.PermissionOverrideCommand;
import com.itravel.platform.modules.identity.api.dto.request.AddressRequest;
import com.itravel.platform.modules.identity.api.dto.request.IdentityCardRequest;
import com.itravel.platform.modules.identity.api.dto.request.PassportRequest;
import com.itravel.platform.modules.identity.api.dto.request.PermissionOverrideRequest;
import com.itravel.platform.modules.identity.api.dto.response.AddressResponse;
import com.itravel.platform.modules.identity.api.dto.response.IdentityCardResponse;
import com.itravel.platform.modules.identity.api.dto.response.PassportResponse;
import com.itravel.platform.modules.identity.domain.role.Role;
import com.itravel.platform.common.domain.enums.Gender;
import com.itravel.platform.modules.identity.domain.role.PermissionType;
import com.itravel.platform.modules.identity.domain.user.UserId;
import com.itravel.platform.modules.identity.domain.user.Email;
import com.itravel.platform.modules.identity.domain.user.FullName;
import com.itravel.platform.modules.identity.domain.user.PhoneNumber;
import com.itravel.platform.modules.identity.domain.user.Avatar;
import com.itravel.platform.modules.identity.domain.role.RoleId;
import com.itravel.platform.modules.identity.domain.role.RoleName;
import com.itravel.platform.modules.identity.domain.role.Permission;
import com.itravel.platform.modules.identity.domain.account.AccountId;
import com.itravel.platform.modules.identity.domain.account.PasswordHash;
import com.itravel.platform.modules.identity.domain.account.ProviderId;
import com.itravel.platform.modules.identity.domain.account.RawPassword;
import com.itravel.platform.modules.identity.domain.account.Username;
import com.itravel.platform.modules.identity.domain.customer.Address;
import com.itravel.platform.modules.identity.domain.customer.IdentityCard;
import com.itravel.platform.modules.identity.domain.customer.Passport;
import com.itravel.platform.modules.identity.domain.customer.CustomerId;
import com.itravel.platform.modules.identity.domain.otp.OtpCode;
import com.itravel.platform.modules.identity.domain.auth.TokenId;
import com.itravel.platform.modules.identity.domain.auth.TokenHash;

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

    default ProviderId toProviderId(String providerId) {
        return providerId != null ? new ProviderId(providerId) : null;
    }
    default String fromProviderId(ProviderId providerId) {
        return providerId != null ? providerId.value() : null;
    }

    default TokenHash toTokenHash(String hash) {
        return hash != null ? new TokenHash(hash) : null;
    }
    default String fromTokenHash(TokenHash hash) {
        return hash != null ? hash.value() : null;
    }

    default PhoneNumber toPhoneNumber(String phoneNumber) {
        return phoneNumber != null && !phoneNumber.isBlank() ? new PhoneNumber(phoneNumber) : null;
    }
    default String fromPhoneNumber(PhoneNumber phoneNumber) {
        return phoneNumber != null ? phoneNumber.value() : null;
    }

    default String fromAvatar(Avatar avatar) {
        return avatar != null ? avatar.value() : null;
    }

    default PermissionType toPermissionType(String type) {
        return type != null && !type.isBlank() ? PermissionType.valueOf(type) : null;
    }
    default String fromPermissionType(PermissionType type) {
        return type != null ? type.name() : null;
    }

    default Gender toGender(String gender) {
        return gender != null && !gender.isBlank() ? Gender.valueOf(gender) : null;
    }
    default String fromGender(Gender gender) {
        return gender != null ? gender.name() : null;
    }

    default PermissionOverrideCommand toPermissionOverrideCommand(PermissionOverrideRequest request) {
        return request != null
                ? new PermissionOverrideCommand(
                toPermission(request.getPermission()),
                toPermissionType(request.getPermissionType())
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

    // Address mappings
    default Address toAddress(AddressRequest request) {
        if (request == null) {
            return null;
        }
        return new Address(
                request.detail(),
                request.wardId(),
                request.provinceId()
        );
    }

    default AddressResponse fromAddress(Address address) {
        if (address == null) {
            return null;
        }
        return new AddressResponse(
                address.detail(),
                address.wardId(),
                address.provinceId()
        );
    }
    // IdentityCard mappings
    default IdentityCard toIdentityCard(IdentityCardRequest request) {
        if (request == null) {
            return null;
        }
        return new IdentityCard(
                request.documentNumber(),
                request.issueDate(),
                request.issuePlace()
        );
    }

    default IdentityCardResponse fromIdentityCard(IdentityCard identityCard) {
        if (identityCard == null) {
            return null;
        }
        return new IdentityCardResponse(
                identityCard.documentNumber(),
                identityCard.issueDate(),
                identityCard.issuePlace()
        );
    }

    // Passport mappings
    default Passport toPassport(PassportRequest request) {
        if (request == null) {
            return null;
        }
        return new Passport(
                request.documentNumber(),
                request.issueDate(),
                request.expiryDate()
        );
    }

    default PassportResponse fromPassport(Passport passport) {
        if (passport == null) {
            return null;
        }
        return new PassportResponse(
                passport.documentNumber(),
                passport.issueDate(),
                passport.expiryDate()
        );
    }


}
