package com.itravel.platform.modules.identity.api.mapper;

import com.itravel.platform.modules.identity.api.dto.request.CreateRoleRequest;
import com.itravel.platform.modules.identity.api.dto.request.UpdatePermissionsForRoleRequest;
import com.itravel.platform.modules.identity.api.dto.request.UpdateRoleRequest;
import com.itravel.platform.modules.identity.api.dto.response.RoleResponse;
import com.itravel.platform.modules.identity.application.command.role.CreateRoleCommand;
import com.itravel.platform.modules.identity.application.command.role.DeleteRoleCommand;
import com.itravel.platform.modules.identity.application.command.role.UpdatePermissionForRoleCommand;
import com.itravel.platform.modules.identity.application.command.role.UpdateRoleCommand;
import com.itravel.platform.modules.identity.domain.aggregate.Role;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.Permission;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface RoleRestMapper {
    @Mapping(target = "id",
            expression = "java(role.getId()!= null ? role.getId().value() : null)")
    @Mapping(target = "name",
            expression = "java(role.getName() != null ? role.getName().value() : null)")
    RoleResponse toRoleResponse(Role role);

    @Mapping(target = "name",
            expression = "java(request.name() != null ? new RoleName(request.name()) : null)")
    @Mapping(target = "status",
            expression = "java(request.status() != null ? RoleStatus.valueOf(request.status()) : null)")
    CreateRoleCommand toCreateRoleCommand(CreateRoleRequest request);

    @Mapping(target = "id",
            expression = "java(id != null ? new RoleId(id) : null)")
    @Mapping(target = "name",
            expression = "java(request.name() != null ? new RoleName(request.name()) : null)")
    @Mapping(target = "status",
            expression = "java(request.status() != null ? RoleStatus.valueOf(request.status()) : null)")
    UpdateRoleCommand toUpdateRoleCommand(Long id, UpdateRoleRequest request);

    @Mapping(target = "id",
            expression = "java(id != null ? new RoleId(id) : null)")
    DeleteRoleCommand toDeleteRoleCommand(Long id);

    @Mapping(target = "id",
            expression = "java(request.id() != null ? new RoleId(request.id()) : null)")
    UpdatePermissionForRoleCommand toUpdatePermissionForRoleCommand(UpdatePermissionsForRoleRequest request);

    List<UpdatePermissionForRoleCommand> toUpdatePermissionForRoleCommand(List<UpdatePermissionsForRoleRequest> request);

    default Permission map(String code) {
        return code != null ? new Permission(code) : null;
    }

    default String map(Permission permission) {
        return permission.code();
    }
}