package com.itravel.platform.modules.tour.domain.tour.exception;

import com.itravel.platform.common.exceptions.DomainErrorCode;
import com.itravel.platform.common.exceptions.DomainException;

public class InvalidMinParticipantsException extends DomainException {
    public InvalidMinParticipantsException() {
        super(DomainErrorCode.INVALID_MIN_PARTICIPANTS);
    }
}

