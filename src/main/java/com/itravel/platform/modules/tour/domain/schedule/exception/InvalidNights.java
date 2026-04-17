package com.itravel.platform.modules.tour.domain.schedule.exception;

import com.itravel.platform.common.exceptions.DomainErrorCode;
import com.itravel.platform.common.exceptions.DomainException;

public class InvalidNights extends DomainException {
    public InvalidNights() {
        super(DomainErrorCode.INVALID_NIGHTS);
    }
}

