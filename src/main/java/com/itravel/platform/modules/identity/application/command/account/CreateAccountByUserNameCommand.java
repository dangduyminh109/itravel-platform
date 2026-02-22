package com.itravel.platform.modules.identity.application.command.account;

import com.itravel.platform.modules.identity.domain.aggregate.valueobject.*;
import java.util.Set;

public record CreateAccountByUserNameCommand(
        Username username,
        RawPassword password,
        Set<RoleId> roleList,
        Set<PermissionOverrideCommand> permissionOverrides,
        UserId id
) {}
