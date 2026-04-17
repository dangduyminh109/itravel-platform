package com.itravel.platform.modules.identity.application.dto;

import lombok.Builder;
import java.time.LocalDate;

@Builder
public record PassportDTO(
        String documentNumber,
        LocalDate issueDate,
        LocalDate expiryDate
) {}
