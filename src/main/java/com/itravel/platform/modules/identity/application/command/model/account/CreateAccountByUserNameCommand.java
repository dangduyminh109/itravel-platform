package com.itravel.platform.modules.identity.application.command.model.account;

import com.itravel.platform.modules.identity.domain.user.*;
import com.itravel.platform.modules.identity.domain.role.*;
import com.itravel.platform.modules.identity.domain.account.*;

import java.util.Set;

public record CreateAccountByUserNameCommand(
        Username username,
        RawPassword password,
        Set<RoleId> roleList,
        Set<PermissionOverrideCommand> permissionOverrides,
        UserId id
) {}
