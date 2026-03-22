package com.itravel.platform.modules.identity.infrastructure.persistence.mapper;

import com.itravel.platform.modules.identity.domain.aggregate.Role;
import com.itravel.platform.modules.identity.domain.aggregate.enums.RoleStatus;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.Permission;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.RoleId;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.RoleName;
import com.itravel.platform.modules.identity.infrastructure.persistence.entity.RoleJpaEntity;
import com.itravel.platform.modules.identity.share.IdentityValueObjectMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import java.util.stream.Collectors;

@Mapper(
        componentModel = "spring",
        uses = {IdentityValueObjectMapper.class},
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface RoleMapper {
    @Mapping(target = "id", ignore = true)
    RoleJpaEntity toRoleJpaEntity(Role role);

    static Role toRoleDomain(RoleJpaEntity entity) {
        return Role.fromExistingBuilder()
                .id(new RoleId(entity.getId()))
                .name(new RoleName(entity.getName()))
                .status(RoleStatus.valueOf(entity.getStatus()))
                .permissionList(entity.getPermissionList().stream()
                        .map(e -> new Permission(e.getCode()))
                        .collect(Collectors.toSet())
                )
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}
