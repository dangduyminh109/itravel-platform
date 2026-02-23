package com.itravel.platform.modules.identity.api.dto.response;

import com.itravel.platform.modules.identity.domain.aggregate.enums.AccountStatus;
import com.itravel.platform.modules.identity.domain.aggregate.enums.Gender;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Set;

public record CustomerResponse(
        String id,
        String fullName,
        String phoneNumber,
        String avatar,
        Gender gender,
        LocalDate dateOfBirth,
        AddressResponse address,
        IdentityCardResponse identityCard,
        PassportResponse passport,
        String email,
        Set<Long> roleList,
        AccountStatus status,
        Instant createdAt,
        Instant updatedAt,
        Instant deletedAt
) {}
