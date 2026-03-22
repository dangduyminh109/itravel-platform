package com.itravel.platform.modules.identity.application.command.role;

import com.itravel.platform.modules.identity.domain.aggregate.enums.RoleStatus;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.Permission;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.RoleName;

import java.util.List;

public record CreateRoleCommand(
        RoleName name,
        RoleStatus status,
        List<Permission> permissionCodeList
) {
}
