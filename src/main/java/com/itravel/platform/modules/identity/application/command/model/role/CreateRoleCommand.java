package com.itravel.platform.modules.identity.application.command.model.role;

import com.itravel.platform.modules.identity.domain.role.RoleStatus;
import com.itravel.platform.modules.identity.domain.role.Permission;
import com.itravel.platform.modules.identity.domain.role.RoleName;

import java.util.List;

public record CreateRoleCommand(
        RoleName name,
        RoleStatus status,
        List<Permission> permissionCodeList
) {
}
