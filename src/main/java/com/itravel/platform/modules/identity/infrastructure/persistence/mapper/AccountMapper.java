package com.itravel.platform.modules.identity.infrastructure.persistence.mapper;

import com.itravel.platform.modules.identity.domain.account.Account;
import com.itravel.platform.modules.identity.domain.role.Role;
import com.itravel.platform.modules.identity.domain.user.*;
import com.itravel.platform.modules.identity.application.dto.AccountDTO;
import com.itravel.platform.modules.identity.domain.account.*;
import com.itravel.platform.modules.identity.infrastructure.persistence.entity.AccountJpaEntity;
import com.itravel.platform.modules.identity.infrastructure.persistence.entity.PermissionJpaEntity;
import com.itravel.platform.modules.identity.infrastructure.persistence.entity.RoleJpaEntity;
import com.itravel.platform.modules.identity.share.IdentityValueObjectMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import java.util.stream.Collectors;

@Mapper(
        componentModel = "spring",
        uses = {
                IdentityValueObjectMapper.class,
                PermissionOverrideMapper.class
        },
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface AccountMapper {
    @Mapping(target = "permissionOverrides", ignore = true)
    AccountJpaEntity toAccountJpaEntity(Account account);

    static Account toAccountDomain(AccountJpaEntity entity) {
        return Account.fromExistingBuilder()
                .id(new AccountId(entity.getId()))
                .password(entity.getPassword() !=null ? new PasswordHash(entity.getPassword()) : null)
                .username(entity.getUsername() !=null ? new Username(entity.getUsername()) : null)
                .email(entity.getEmail() !=null ? new Email(entity.getEmail()) : null)
                .authProvider(entity.getAuthProvider())
                .providerId(entity.getProviderId() !=null ? new ProviderId(entity.getProviderId()) : null)
                .status(entity.getStatus())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .deletedAt(entity.getDeletedAt())
                .roleList(entity.getRoleList().stream()
                        .map(RoleMapper::toRoleDomain).collect(Collectors.toSet())
                )
                .permissionOverrides(entity.getPermissionOverrides().stream()
                        .map(PermissionOverrideMapper::toPermissionOverrideDomain).collect(Collectors.toSet())
                )
                .build();
    }

    default RoleJpaEntity toRoleJpaEntity(Role role){
        return RoleJpaEntity.builder()
                .id(role.getId().value())
                .name(role.getName().value())
                .status(role.getStatus().toString())
                .permissionList(role.getPermissionList().stream()
                        .map(e -> new PermissionJpaEntity(e.code()))
                        .collect(Collectors.toSet()))
                .build();
    }

    static AccountDTO toAccountDTO(AccountJpaEntity entity) {
        return AccountDTO.builder()
                .id(entity.getId())
                .username(entity.getUsername())
                .email(entity.getEmail())
                .status(entity.getStatus() != null ? entity.getStatus().name() : null)
                .roleList(entity.getRoleList().stream()
                        .map(RoleMapper::toRoleDTO)
                        .collect(Collectors.toSet()))
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .deletedAt(entity.getDeletedAt())
                .build();
    }
}
