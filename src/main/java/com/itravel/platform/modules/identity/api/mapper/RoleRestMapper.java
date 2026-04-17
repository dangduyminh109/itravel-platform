package com.itravel.platform.modules.identity.api.mapper;

import com.itravel.platform.modules.identity.api.dto.request.CreateRoleRequest;
import com.itravel.platform.modules.identity.api.dto.request.UpdatePermissionsForRoleRequest;
import com.itravel.platform.modules.identity.api.dto.request.UpdateRoleRequest;
import com.itravel.platform.modules.identity.api.dto.response.RoleResponse;
import com.itravel.platform.modules.identity.application.command.model.role.CreateRoleCommand;
import com.itravel.platform.modules.identity.application.command.model.role.DeleteRoleCommand;
import com.itravel.platform.modules.identity.application.command.model.role.UpdatePermissionForRoleCommand;
import com.itravel.platform.modules.identity.application.command.model.role.UpdateRoleCommand;
import com.itravel.platform.modules.identity.application.dto.RoleDTO;
import com.itravel.platform.modules.identity.domain.role.Role;
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
    RoleResponse toRoleResponse(RoleDTO role);
    List<RoleResponse> toRoleResponseList(List<RoleDTO> roles);
    CreateRoleCommand toCreateRoleCommand(CreateRoleRequest request);
    UpdateRoleCommand toUpdateRoleCommand(Long id, UpdateRoleRequest request);
    DeleteRoleCommand toDeleteRoleCommand(Long id);
    UpdatePermissionForRoleCommand toUpdatePermissionForRoleCommand(UpdatePermissionsForRoleRequest request);
    List<UpdatePermissionForRoleCommand> toUpdatePermissionForRoleCommand(
            List<UpdatePermissionsForRoleRequest> request
    );
}