package com.itravel.platform.modules.identity.infrastructure.persistence.mapper;

import com.itravel.platform.modules.identity.domain.aggregate.Role;
import com.itravel.platform.modules.identity.domain.aggregate.enums.RoleStatus;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.Permission;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.RoleId;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.RoleName;
import com.itravel.platform.modules.identity.infrastructure.persistence.entity.RoleJpaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface RoleMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name",
            expression = "java(role.getName() != null ? role.getName().value() : null)")
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
                .build();
    }
}
