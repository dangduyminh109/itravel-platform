package com.itravel.platform.modules.tour.domain.tour.exception;

import com.itravel.platform.common.exceptions.DomainErrorCode;
import com.itravel.platform.common.exceptions.DomainException;

public class InvalidParticipantsRangeException extends DomainException {
    public InvalidParticipantsRangeException() {
        super(DomainErrorCode.INVALID_PARTICIPANTS_RANGE);
    }
}

