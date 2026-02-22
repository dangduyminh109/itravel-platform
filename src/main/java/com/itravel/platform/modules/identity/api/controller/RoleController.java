package com.itravel.platform.modules.identity.api.controller;

import com.itravel.platform.common.dto.ApiResponse;
import com.itravel.platform.modules.identity.api.dto.request.CreateRoleRequest;
import com.itravel.platform.modules.identity.api.dto.request.UpdateRoleRequest;
import com.itravel.platform.modules.identity.api.dto.response.RoleResponse;
import com.itravel.platform.modules.identity.api.mapper.RoleRestMapper;
import com.itravel.platform.modules.identity.application.command.role.CreateRoleCommand;
import com.itravel.platform.modules.identity.application.command.role.UpdateRoleCommand;
import com.itravel.platform.modules.identity.application.command.role.UpdatePermissionForRoleCommand;
import com.itravel.platform.modules.identity.api.dto.request.UpdatePermissionsForRoleRequest;
import com.itravel.platform.modules.identity.application.handler.RoleCommandHandler;
import com.itravel.platform.modules.identity.application.service.RoleQueryService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/role")
public class RoleController {
    RoleCommandHandler roleCommandHandler;
    RoleQueryService roleQueryService;
    RoleRestMapper mapper;

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_VIEW')")
    public ApiResponse<List<RoleResponse>> getRoles() {
        return ApiResponse.<List<RoleResponse>>builder()
                .success(true)
                .data(roleQueryService.getRoles().stream().map(mapper::toRoleResponse).toList())
                .build();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_CREATE')")
    public ApiResponse<RoleResponse> create(@RequestBody CreateRoleRequest createRoleRequest) {
        CreateRoleCommand createRoleCommand = mapper.toCreateRoleCommand(createRoleRequest);
        return ApiResponse.<RoleResponse>builder()
                .message("Create role successfully")
                .success(true)
                .data(mapper.toRoleResponse(roleCommandHandler.create(createRoleCommand)))
                .build();
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_UPDATE')")
    public ApiResponse<RoleResponse> update(@PathVariable Long id, @RequestBody UpdateRoleRequest updateRoleRequest) {
        UpdateRoleCommand updateRoleCommand = mapper.toUpdateRoleCommand(id,updateRoleRequest);
        return ApiResponse.<RoleResponse>builder()
                .message("Update role successfully")
                .success(true)
                .data(mapper.toRoleResponse(roleCommandHandler.update(updateRoleCommand)))
                .build();
    }

    @DeleteMapping("/{id}/destroy")
    @PreAuthorize("hasAuthority('ROLE_DELETE')")
    public ApiResponse<Void> destroy(@PathVariable Long id) {
        roleCommandHandler.destroy(mapper.toDeleteRoleCommand(id));
        return ApiResponse.<Void>builder()
                .message("Destroy role successfully")
                .success(true)
                .build();
    }

    @PutMapping("/permissions")
    @PreAuthorize("hasAuthority('ROLE_UPDATE')")
    public ApiResponse<Void> updatePermissionForRole(@RequestBody List<UpdatePermissionsForRoleRequest> requests) {
        List<UpdatePermissionForRoleCommand> updatePermissionForRoleCommand =
                mapper.toUpdatePermissionForRoleCommand(requests);
        roleCommandHandler.updatePermissionForRole(updatePermissionForRoleCommand);
        return ApiResponse.<Void>builder()
                .message("Update role successfully")
                .success(true)
                .build();
    }
}
