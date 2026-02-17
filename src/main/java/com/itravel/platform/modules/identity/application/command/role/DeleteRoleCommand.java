package com.itravel.platform.modules.identity.application.command.role;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.RoleId;

public record DeleteRoleCommand(RoleId id) {
}
