package com.itravel.platform.modules.identity.infrastructure.persistence.mapper;

import com.itravel.platform.modules.identity.domain.role.Role;
import com.itravel.platform.modules.identity.domain.role.RoleStatus;
import com.itravel.platform.modules.identity.application.dto.RoleDTO;
import com.itravel.platform.modules.identity.domain.role.Permission;
import com.itravel.platform.modules.identity.domain.role.RoleId;
import com.itravel.platform.modules.identity.domain.role.RoleName;
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
                                                .collect(Collectors.toSet()))
                                .createdAt(entity.getCreatedAt())
                                .updatedAt(entity.getUpdatedAt())
                                .build();
        }

        static RoleDTO toRoleDTO(RoleJpaEntity entity) {
                return RoleDTO.builder()
                                .id(entity.getId())
                                .name(entity.getName())
                                .status(entity.getStatus())
                                .permissionList(entity.getPermissionList().stream()
                                                .map(com.itravel.platform.modules.identity.infrastructure.persistence.entity.PermissionJpaEntity::getCode)
                                                .collect(Collectors.toSet()))
                                .build();
        }
}
