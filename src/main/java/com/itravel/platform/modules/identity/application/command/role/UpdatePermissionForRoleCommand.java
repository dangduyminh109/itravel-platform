package com.itravel.platform.modules.identity.application.command.role;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.RoleId;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.Permission;
import java.util.List;

public record UpdatePermissionForRoleCommand(RoleId id, List<Permission> permissionCodeList) {
}
