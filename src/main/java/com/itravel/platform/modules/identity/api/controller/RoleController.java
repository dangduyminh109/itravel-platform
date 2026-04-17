package com.itravel.platform.modules.identity.api.controller;

import com.itravel.platform.common.dto.ApiResponse;
import com.itravel.platform.modules.identity.api.dto.request.CreateRoleRequest;
import com.itravel.platform.modules.identity.api.dto.request.UpdateRoleRequest;
import com.itravel.platform.modules.identity.api.dto.response.RoleResponse;
import com.itravel.platform.modules.identity.api.mapper.RoleRestMapper;
import com.itravel.platform.modules.identity.application.command.model.role.CreateRoleCommand;
import com.itravel.platform.modules.identity.application.command.model.role.UpdateRoleCommand;
import com.itravel.platform.modules.identity.application.command.model.role.UpdatePermissionForRoleCommand;
import com.itravel.platform.modules.identity.api.dto.request.UpdatePermissionsForRoleRequest;
import com.itravel.platform.modules.identity.application.port.in.role.facade.RoleCommandFacade;
import com.itravel.platform.modules.identity.application.port.in.role.facade.RoleQueryFacade;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/role")
public class RoleController {
    RoleCommandFacade roleCommandFacade;
    RoleQueryFacade roleQueryFacade;
    RoleRestMapper mapper;

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_VIEW')")
    public ApiResponse<List<RoleResponse>> getRoles(
            @RequestParam(required = false) String status,
            @RequestParam (required = false) String keyword
    ) {
        return ApiResponse.<List<RoleResponse>>builder()
                .success(true)
                .response(mapper.toRoleResponseList(roleQueryFacade.getRoles(status, keyword)))
                .build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ROLE_CREATE')")
    public ApiResponse<RoleResponse> create(@RequestBody @Valid CreateRoleRequest createRoleRequest) {
        CreateRoleCommand createRoleCommand = mapper.toCreateRoleCommand(createRoleRequest);
        return ApiResponse.<RoleResponse>builder()
                .message("Create role successfully")
                .success(true)
                .response(mapper.toRoleResponse(roleCommandFacade.create(createRoleCommand)))
                .build();
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_UPDATE')")
    public ApiResponse<RoleResponse> update(@PathVariable Long id, @RequestBody @Valid UpdateRoleRequest updateRoleRequest) {
        UpdateRoleCommand updateRoleCommand = mapper.toUpdateRoleCommand(id, updateRoleRequest);
        return ApiResponse.<RoleResponse>builder()
                .message("Update role successfully")
                .success(true)
                .response(mapper.toRoleResponse(roleCommandFacade.update(updateRoleCommand)))
                .build();
    }

    @DeleteMapping("/{id}/destroy")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ROLE_DELETE')")
    public ApiResponse<Void> destroy(@PathVariable Long id) {
        roleCommandFacade.destroy(mapper.toDeleteRoleCommand(id));
        return ApiResponse.<Void>builder()
                .message("Destroy role successfully")
                .success(true)
                .build();
    }

    @PutMapping("/permissions")
    @PreAuthorize("hasAuthority('ROLE_UPDATE')")
    public ApiResponse<Void> updatePermissionForRole(@RequestBody @Valid List<UpdatePermissionsForRoleRequest> requests) {
        List<UpdatePermissionForRoleCommand> updatePermissionForRoleCommand =
                mapper.toUpdatePermissionForRoleCommand(requests);
        roleCommandFacade.updatePermissionForRole(updatePermissionForRoleCommand);
        return ApiResponse.<Void>builder()
                .message("Update role successfully")
                .success(true)
                .build();
    }
}
