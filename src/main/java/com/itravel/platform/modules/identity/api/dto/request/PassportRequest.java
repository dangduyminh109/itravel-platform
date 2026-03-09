package com.itravel.platform.modules.identity.api.dto.request;

import com.itravel.platform.modules.identity.api.dto.request.validation.FutureDate;
import com.itravel.platform.modules.identity.api.dto.request.validation.IssueDateBeforeExpiryDate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@IssueDateBeforeExpiryDate(message = "ISSUE_DATE_MUST_BE_BEFORE_EXPIRY_DATE")
public record PassportRequest(
        @NotBlank(message = "DOCUMENT_NUMBER_CANNOT_BE_BLANK")
        String documentNumber,

        @NotNull(message = "ISSUE_DATE_CANNOT_BE_NULL")
        @DateTimeFormat(pattern = "dd/MM/yyyy")
        LocalDate issueDate,

        @NotNull(message = "EXPIRY_DATE_CANNOT_BE_NULL")
        @FutureDate(message = "EXPIRY_DATE_MUST_BE_FUTURE")
        @DateTimeFormat(pattern = "dd/MM/yyyy")
        LocalDate expiryDate
) {}

