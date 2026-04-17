package com.itravel.platform.modules.identity.application.dto;

import lombok.Builder;
import java.time.LocalDate;

@Builder
public record IdentityCardDTO(
        String documentNumber,
        LocalDate issueDate,
        String issuePlace
) {}
