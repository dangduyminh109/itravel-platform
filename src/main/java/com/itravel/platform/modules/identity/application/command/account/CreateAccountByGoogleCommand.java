package com.itravel.platform.modules.identity.application.command.account;

import com.itravel.platform.modules.identity.domain.aggregate.valueobject.PasswordHash;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.RoleId;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.UserId;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.Username;
import java.util.Set;

public record CreateAccountByUserNameCommand(
        Username username,
        PasswordHash password,
        Set<RoleId> roleList,
        UserId userId
) {}
