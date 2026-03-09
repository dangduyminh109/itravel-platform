package com.itravel.platform.modules.identity.application.query;

import com.itravel.platform.modules.identity.application.command.account.PermissionOverrideCommand;
import com.itravel.platform.modules.identity.domain.aggregate.Role;
import com.itravel.platform.modules.identity.domain.aggregate.enums.AccountStatus;
import com.itravel.platform.modules.identity.domain.aggregate.enums.Gender;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.*;
import lombok.Builder;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Set;

@Builder
public record UserDetail(
    UserId id,
    Username username,
    FullName fullName,
    PhoneNumber phoneNumber,
    Avatar avatar,
    Gender gender,
    Email email,
    LocalDate dateOfBirth,
    Set<Role> roleList,
    Set<PermissionOverrideCommand> permissionOverrides,
    AccountStatus status,
    Instant createdAt,
    Instant updatedAt,
    Instant deletedAt
) {}
