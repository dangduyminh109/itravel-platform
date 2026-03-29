package com.itravel.platform.modules.tour.domain.exception;

import com.itravel.platform.common.exceptions.DomainErrorCode;
import com.itravel.platform.common.exceptions.DomainException;

public class InvalidScheduleIdException extends DomainException {
    public InvalidScheduleIdException() {
        super(DomainErrorCode.INVALID_SCHEDULE_ID);
    }
}

