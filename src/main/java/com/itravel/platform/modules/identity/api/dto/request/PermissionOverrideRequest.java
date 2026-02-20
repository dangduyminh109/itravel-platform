package com.itravel.platform.modules.identity.api.dto.request;

import com.itravel.platform.modules.identity.domain.aggregate.enums.PermissionType;

    public record PermissionOverrideRequest(
            String permission,
            PermissionType permissionType
    ){
    }
