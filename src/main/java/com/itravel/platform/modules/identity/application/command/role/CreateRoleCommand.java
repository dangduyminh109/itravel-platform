package com.itravel.platform.modules.identity.application.command.role;

import com.itravel.platform.modules.identity.domain.aggregate.enums.RoleStatus;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.RoleName;

public record CreateRoleCommand(RoleName name, RoleStatus status) {
}
