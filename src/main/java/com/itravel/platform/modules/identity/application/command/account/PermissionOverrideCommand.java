package com.itravel.platform.modules.identity.application.command.account;

import com.itravel.platform.modules.identity.domain.aggregate.enums.PermissionType;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.Permission;

public record PermissionOverrideCommand(
        Permission permission,
        PermissionType permissionType
) {}