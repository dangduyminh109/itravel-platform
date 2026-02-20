package com.itravel.platform.modules.identity.application.command.user;

import com.itravel.platform.modules.identity.application.command.account.PermissionOverrideCommand;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.*;

import java.util.Set;

public record CreateUserCommand(
        Username username,
        PasswordHash password,
        FullName fullName,
        Set<RoleId> roleList,
        Set<PermissionOverrideCommand> permissionOverrides
) {}
