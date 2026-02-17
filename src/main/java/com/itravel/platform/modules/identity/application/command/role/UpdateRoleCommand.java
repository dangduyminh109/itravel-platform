package com.itravel.platform.modules.identity.application.command.role;

import com.itravel.platform.modules.identity.domain.aggregate.enums.RoleStatus;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.RoleId;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.RoleName;

public record UpdateRoleCommand(RoleId id, RoleName name, RoleStatus status) {
}
