package com.itravel.platform.modules.identity.application.command.user;

import com.itravel.platform.modules.identity.domain.aggregate.valueobject.FullName;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.PasswordHash;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.RoleId;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.Username;
import java.util.Set;

public record CreateUserCommand(
        Username username,
        PasswordHash password,
        FullName fullName,
        Set<RoleId> roleList
) {}
