package com.itravel.platform.modules.identity.infrastructure.persistence.mapper;

import com.itravel.platform.modules.identity.domain.aggregate.Account;
import com.itravel.platform.modules.identity.domain.aggregate.Role;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.*;
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
}
