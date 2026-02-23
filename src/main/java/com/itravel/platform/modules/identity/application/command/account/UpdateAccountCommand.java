package com.itravel.platform.modules.identity.application.command.account;
import com.itravel.platform.modules.identity.domain.aggregate.enums.AccountStatus;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.RoleId;

import java.util.Set;

public record UpdateAccountCommand(
        String targetId,
        AccountStatus Status,
        Set<RoleId> roleList,
        Set<PermissionOverrideCommand> permissionOverrides
) {
}