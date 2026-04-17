package com.itravel.platform.modules.tour.domain.tour.exception;

import com.itravel.platform.common.exceptions.DomainErrorCode;
import com.itravel.platform.common.exceptions.DomainException;

public class InvalidDays extends DomainException {
    public InvalidDays() {
        super(DomainErrorCode.INVALID_DAYS);
    }
}

