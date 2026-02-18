package com.itravel.platform.modules.identity.application.command.user;

import com.itravel.platform.modules.identity.domain.aggregate.valueobject.FullName;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.PasswordHash;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.RoleId;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.UserId;

import java.util.Set;

public record UpdateUserCommand(
        UserId userId,
        FullName fullName,
        PasswordHash password,
        Set<RoleId> roleList) {
}
