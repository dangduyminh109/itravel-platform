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
import com.itravel.platform.modules.identity.share.IdentityValueObjectMapper;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(
        componentModel = "spring",
        uses = IdentityValueObjectMapper.class,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface RoleRestMapper {
    RoleResponse toRoleResponse(Role role);
    CreateRoleCommand toCreateRoleCommand(CreateRoleRequest request);
    UpdateRoleCommand toUpdateRoleCommand(Long id, UpdateRoleRequest request);
    DeleteRoleCommand toDeleteRoleCommand(Long id);
    UpdatePermissionForRoleCommand toUpdatePermissionForRoleCommand(UpdatePermissionsForRoleRequest request);
    List<UpdatePermissionForRoleCommand> toUpdatePermissionForRoleCommand(
            List<UpdatePermissionsForRoleRequest> request
    );
}