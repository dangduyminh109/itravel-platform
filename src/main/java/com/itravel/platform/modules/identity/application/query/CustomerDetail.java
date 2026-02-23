package com.itravel.platform.modules.identity.application.query;

import com.itravel.platform.modules.identity.domain.aggregate.Role;
import com.itravel.platform.modules.identity.domain.aggregate.enums.AccountStatus;
import com.itravel.platform.modules.identity.domain.aggregate.enums.Gender;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.*;
import lombok.Builder;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Set;

@Builder
public record CustomerDetail(
    CustomerId id,
    FullName fullName,
    PhoneNumber phoneNumber,
    Avatar avatar,
    Gender gender,
    LocalDate dateOfBirth,
    Address address,
    IdentityCard identityCard,
    Passport passport,
    Email email,
    Set<Role> roleList,
    AccountStatus status,
    Instant createdAt,
    Instant updatedAt,
    Instant deletedAt
) {}
