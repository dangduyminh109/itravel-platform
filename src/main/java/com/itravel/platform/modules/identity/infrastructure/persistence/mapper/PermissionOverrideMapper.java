package com.itravel.platform.modules.identity.infrastructure.persistence.mapper;

import com.itravel.platform.modules.identity.domain.aggregate.PermissionOverride;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.AccountId;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.Permission;
import com.itravel.platform.modules.identity.infrastructure.persistence.entity.PermissionOverrideJpaEntity;
import com.itravel.platform.modules.identity.share.IdentityValueObjectMapper;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        uses = {IdentityValueObjectMapper.class},
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface PermissionOverrideMapper {
    default PermissionOverrideJpaEntity toPermissionOverrideJpaEntity(PermissionOverride permissionOverride) {
        return PermissionOverrideJpaEntity.builder()
                .id(permissionOverride.getId())
                .permission(permissionOverride.getPermission().code())
                .permissionType(permissionOverride.getPermissionType())
                .accountId(permissionOverride.getAccountId().value())
                .build();
    }

    static PermissionOverride toPermissionOverrideDomain(PermissionOverrideJpaEntity entity) {
        return PermissionOverride.fromExistingBuilder()
                .id(entity.getId())
                .permission(new Permission(entity.getPermission()))
                .accountId(new AccountId(entity.getAccountId()))
                .type(entity.getPermissionType())
                .build();
    }
}
