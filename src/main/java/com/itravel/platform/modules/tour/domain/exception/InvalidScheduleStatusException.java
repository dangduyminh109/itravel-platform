package com.itravel.platform.modules.tour.domain.exception;

import com.itravel.platform.common.exceptions.DomainErrorCode;
import com.itravel.platform.common.exceptions.DomainException;

public class InvalidScheduleStatusException extends DomainException {
    public InvalidScheduleStatusException() {
        super(DomainErrorCode.INVALID_SCHEDULE_STATUS);
    }
}

