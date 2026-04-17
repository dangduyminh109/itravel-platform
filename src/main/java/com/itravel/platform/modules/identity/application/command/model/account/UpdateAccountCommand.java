package com.itravel.platform.modules.identity.application.command.model.account;

import com.itravel.platform.modules.identity.domain.account.AccountStatus;
import com.itravel.platform.modules.identity.domain.role.RoleId;

import java.util.Set;

public record UpdateAccountCommand(
        String targetId,
        AccountStatus status,
        Set<RoleId> roleList,
        Set<PermissionOverrideCommand> permissionOverrides
) {
}
