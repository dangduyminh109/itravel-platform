package com.itravel.platform.modules.booking.domain.exception;

import com.itravel.platform.common.exceptions.DomainErrorCode;
import com.itravel.platform.common.exceptions.DomainException;

public class InvalidDateOfBirthException extends DomainException {
    public InvalidDateOfBirthException() {
        super(DomainErrorCode.INVALID_DATE_OF_BIRTH);
    }
}
