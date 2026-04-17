package com.itravel.platform.modules.identity.api.dto.response;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Set;

public record CustomerResponse(
        String id,
        String fullName,
        String phoneNumber,
        String avatar,
        String gender,
        LocalDate dateOfBirth,
        AddressResponse address,
        IdentityCardResponse identityCard,
        PassportResponse passport,
        String email,
        Set<RoleResponse> roleList,
        String status,
        Instant createdAt,
        Instant updatedAt,
        Instant deletedAt
) {}
