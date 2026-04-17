package com.itravel.platform.modules.identity.infrastructure.persistence.mapper;

import com.itravel.platform.modules.identity.domain.user.User;
import com.itravel.platform.modules.identity.domain.user.UserId;
import com.itravel.platform.modules.identity.domain.user.FullName;
import com.itravel.platform.modules.identity.domain.user.PhoneNumber;
import com.itravel.platform.modules.identity.domain.user.Avatar;
import com.itravel.platform.modules.identity.domain.user.Email;
import com.itravel.platform.modules.identity.application.dto.PermissionOverrideDTO;
import com.itravel.platform.modules.identity.application.dto.RoleDTO;
import com.itravel.platform.modules.identity.application.dto.UserDetailDTO;
import com.itravel.platform.modules.identity.infrastructure.persistence.entity.UserJpaEntity;
import com.itravel.platform.modules.identity.infrastructure.persistence.entity.AccountJpaEntity;
import com.itravel.platform.modules.identity.share.IdentityValueObjectMapper;
import java.util.Set;
import java.util.stream.Collectors;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        uses = {IdentityValueObjectMapper.class},
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface UserMapper {
    UserJpaEntity toUserJpaEntity(User user);

    static User toUserDomain(UserJpaEntity entity) {
        return User.fromExistingBuilder()
                .id(new UserId(entity.getId()))
                .fullName(new FullName(entity.getFullName()))
                .phoneNumber(entity.getPhoneNumber() != null ? new PhoneNumber(entity.getPhoneNumber()) : null)
                .avatar(entity.getAvatar() != null ? new Avatar(entity.getAvatar()) : null)
                .gender(entity.getGender())
                .email(entity.getEmail() != null ? new Email(entity.getEmail()) : null)
                .dateOfBirth(entity.getDateOfBirth())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .deletedAt(entity.getDeletedAt())
                .build();
    }


    static UserDetailDTO toUserDetailDTO(UserJpaEntity entity, AccountJpaEntity account) {
        return UserDetailDTO.builder()
                .id(entity.getId())
                .fullName(entity.getFullName())
                .phoneNumber(entity.getPhoneNumber())
                .avatar(entity.getAvatar())
                .gender(entity.getGender() != null ? entity.getGender().name() : null)
                .email(entity.getEmail())
                .dateOfBirth(entity.getDateOfBirth())
                .username(account != null ? account.getUsername() : null)
                .status(account != null ? account.getStatus().name() : null)
                .roleList(account != null ? mapRoles(account) : Set.of())
                .permissionOverrides(account != null ? mapPermissionOverrides(account) : Set.of())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .deletedAt(entity.getDeletedAt())
                .build();
    }

    static Set<RoleDTO> mapRoles(AccountJpaEntity account) {
        return account.getRoleList().stream()
                .map(role -> RoleDTO.builder()
                        .id(role.getId())
                        .name(role.getName())
                        .status(role.getStatus() != null ? role.getStatus() : null)
                        .permissionList(role.getPermissionList().stream().map(p -> p.getCode()).collect(Collectors.toSet()))
                        .build()
                )
                .collect(Collectors.toSet());
    }

    static Set<PermissionOverrideDTO> mapPermissionOverrides(AccountJpaEntity account) {
        return account.getPermissionOverrides().stream()
                .map(po -> PermissionOverrideDTO.builder()
                        .permission(po.getPermission())
                        .permissionType(po.getPermissionType().name())
                        .build())
                .collect(Collectors.toSet());
    }
}

