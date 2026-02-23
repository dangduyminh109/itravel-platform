package com.itravel.platform.modules.identity.api.dto.request;

import com.itravel.platform.modules.identity.api.dto.request.validation.PastOrPresentDate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record IdentityCardRequest(
        @NotBlank(message = "DOCUMENT_NUMBER_CANNOT_BE_BLANK")
        String documentNumber,

        @NotNull(message = "ISSUE_DATE_CANNOT_BE_NULL")
        @PastOrPresentDate(message = "ISSUE_DATE_MUST_BE_PAST_OR_PRESENT")
        LocalDate issueDate,

        @NotBlank(message = "ISSUE_PLACE_CANNOT_BE_BLANK")
        String issuePlace
) {}

