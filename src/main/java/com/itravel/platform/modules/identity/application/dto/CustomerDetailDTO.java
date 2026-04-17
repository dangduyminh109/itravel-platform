package com.itravel.platform.modules.identity.application.dto;

import lombok.Builder;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Set;

@Builder
public record CustomerDetailDTO(
        String id,
        String fullName,
        String phoneNumber,
        String avatar,
        String gender,
        LocalDate dateOfBirth,
        AddressDTO address,
        IdentityCardDTO identityCard,
        PassportDTO passport,
        String email,
        Set<RoleDTO> roleList,
        String status,
        Instant createdAt,
        Instant updatedAt,
        Instant deletedAt
) {}

