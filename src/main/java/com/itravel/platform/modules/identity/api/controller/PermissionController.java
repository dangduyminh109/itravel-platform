package com.itravel.platform.modules.identity.api.controller;

import com.itravel.platform.common.dto.ApiResponse;
import com.itravel.platform.modules.identity.api.dto.response.PermissionResponse;
import com.itravel.platform.modules.identity.api.mapper.PermissionRestMapper;
import com.itravel.platform.modules.identity.application.port.in.permission.facade.PermissionQueryFacade;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/permission")
public class PermissionController {
    PermissionQueryFacade permissionQueryFacade;
    PermissionRestMapper mapper;

    @GetMapping
    public ApiResponse<List<PermissionResponse>> getPermission() {
        return ApiResponse.<List<PermissionResponse>>builder()
                .message("Get permissions successfully")
                .response(mapper.toPermissionResponseList(permissionQueryFacade.getPermissions()))
                .success(true)
                .build();
    }
}
