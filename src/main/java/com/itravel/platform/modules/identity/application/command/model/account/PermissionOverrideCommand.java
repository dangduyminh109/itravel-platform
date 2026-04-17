package com.itravel.platform.modules.identity.application.command.model.account;

import com.itravel.platform.modules.identity.domain.role.PermissionType;
import com.itravel.platform.modules.identity.domain.role.Permission;

public record PermissionOverrideCommand(
        Permission permission,
        PermissionType permissionType
) {}
