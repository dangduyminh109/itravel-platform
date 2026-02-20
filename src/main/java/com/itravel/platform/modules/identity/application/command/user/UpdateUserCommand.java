package com.itravel.platform.modules.identity.application.command.user;

import com.itravel.platform.modules.identity.application.command.account.PermissionOverrideCommand;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.*;

import java.util.Set;

public record UpdateUserCommand(
        UserId id,
        FullName fullName,
        PasswordHash newPassword,
        Set<RoleId> roleList,
        Set<PermissionOverrideCommand> permissionOverrides
) {
}
