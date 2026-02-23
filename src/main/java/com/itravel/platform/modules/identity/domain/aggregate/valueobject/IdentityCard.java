package com.itravel.platform.modules.identity.domain.aggregate.valueobject;

import com.itravel.platform.common.exceptions.DomainErrorCode;
import com.itravel.platform.modules.identity.domain.exception.InvalidIdentityCardException;

import java.time.LocalDate;

public record IdentityCard(
        String documentNumber,
        LocalDate issueDate,
        String issuePlace
) {
    public IdentityCard {
        if (documentNumber == null || documentNumber.isBlank()) {
            throw new InvalidIdentityCardException(DomainErrorCode.DOCUMENT_NUMBER_CANNOT_BE_BLANK);
        }
        if (issueDate == null) {
            throw new InvalidIdentityCardException(DomainErrorCode.ISSUE_DATE_CANNOT_BE_NULL);
        }
        if (issueDate.isAfter(LocalDate.now())) {
            throw new InvalidIdentityCardException(DomainErrorCode.ISSUE_DATE_MUST_BE_PAST_OR_PRESENT);
        }
        if (issuePlace == null || issuePlace.isBlank()) {
            throw new InvalidIdentityCardException(DomainErrorCode.ISSUE_PLACE_CANNOT_BE_BLANK);
        }
    }
}


