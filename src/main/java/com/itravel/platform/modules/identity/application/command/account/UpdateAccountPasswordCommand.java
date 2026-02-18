package com.itravel.platform.modules.identity.application.command.account;

import com.itravel.platform.modules.identity.domain.aggregate.valueobject.FullName;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.PasswordHash;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.RoleId;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.UserId;

import java.util.Set;

public record UpdateAccountCommand(
        UserId userId,
        FullName fullName,
        PasswordHash password,
        Set<RoleId> roleList) {
}
