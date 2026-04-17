package com.itravel.platform.modules.identity.application.command.model.role;

import com.itravel.platform.modules.identity.domain.role.RoleStatus;
import com.itravel.platform.modules.identity.domain.role.RoleId;
import com.itravel.platform.modules.identity.domain.role.RoleName;

public record UpdateRoleCommand(RoleId id, RoleName name, RoleStatus status) {
}
