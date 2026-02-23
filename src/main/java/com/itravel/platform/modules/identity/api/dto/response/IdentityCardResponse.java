package com.itravel.platform.modules.identity.api.dto.response;

import java.time.LocalDate;

public record IdentityCardResponse(
        String documentNumber,
        LocalDate issueDate,
        String issuePlace
) {}

