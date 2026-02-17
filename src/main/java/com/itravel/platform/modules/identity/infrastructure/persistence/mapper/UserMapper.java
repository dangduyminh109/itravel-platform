package com.itravel.platform.modules.identity.infrastructure.persistence.mapper;

import com.itravel.platform.modules.identity.domain.aggregate.Role;
import com.itravel.platform.modules.identity.domain.aggregate.User;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.FullName;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.UserId;
import com.itravel.platform.modules.identity.infrastructure.persistence.entity.PermissionJpaEntity;
import com.itravel.platform.modules.identity.infrastructure.persistence.entity.RoleJpaEntity;
import com.itravel.platform.modules.identity.infrastructure.persistence.entity.UserJpaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {
    @Mapping(target = "id",
            expression = "java(user.getId() != null ? user.getId().value() : null)")
    @Mapping(target = "fullName",
            expression = "java(user.getFullName() != null ? user.getFullName().value() : null)")
    UserJpaEntity toUserJpaEntity(User user);

    static User toUserDomain(UserJpaEntity entity) {
        return User.fromExistingBuilder()
                .id(new UserId(entity.getId()))
                .fullName( new FullName(entity.getFullName()))
                .roleList(entity.getRoleList().stream()
                        .map(RoleMapper::toRoleDomain).collect(Collectors.toSet())
                )
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .deletedAt(entity.getDeletedAt())
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
