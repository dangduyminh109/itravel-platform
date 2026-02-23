package com.itravel.platform.modules.identity.api.dto.response;

import java.time.LocalDate;

public record PassportResponse(
        String documentNumber,
        LocalDate issueDate,
        LocalDate expiryDate
) {}

