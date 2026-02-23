package com.itravel.platform.modules.identity.domain.aggregate.valueobject;

import com.itravel.platform.common.exceptions.DomainErrorCode;
import com.itravel.platform.modules.identity.domain.exception.InvalidPassportException;

import java.time.LocalDate;

public record Passport(
        String documentNumber,
        LocalDate issueDate,
        LocalDate expiryDate
) {
    public Passport {
        if (documentNumber == null || documentNumber.isBlank()) {
            throw new InvalidPassportException(DomainErrorCode.DOCUMENT_NUMBER_CANNOT_BE_BLANK);
        }
        if (issueDate == null) {
            throw new InvalidPassportException(DomainErrorCode.ISSUE_DATE_CANNOT_BE_NULL);
        }
        if (expiryDate == null) {
            throw new InvalidPassportException(DomainErrorCode.EXPIRY_DATE_CANNOT_BE_NULL);
        }
        if (!expiryDate.isAfter(LocalDate.now())) {
            throw new InvalidPassportException(DomainErrorCode.EXPIRY_DATE_MUST_BE_FUTURE);
        }
        if (!issueDate.isBefore(expiryDate)) {
            throw new InvalidPassportException(DomainErrorCode.ISSUE_DATE_MUST_BE_BEFORE_EXPIRY_DATE);
        }
    }
}


