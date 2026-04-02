package com.itravel.platform.modules.tour.domain.tour.exception;

import com.itravel.platform.common.exceptions.DomainErrorCode;
import com.itravel.platform.common.exceptions.DomainException;

public class InvalidDaysNightsRelationException extends DomainException {
    public InvalidDaysNightsRelationException() {
        super(DomainErrorCode.INVALID_DAYS_NIGHTS_RELATION);
    }
}

