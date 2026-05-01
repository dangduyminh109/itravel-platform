package com.itravel.platform.modules.booking.domain.exception;

import com.itravel.platform.common.exceptions.DomainErrorCode;
import com.itravel.platform.common.exceptions.DomainException;

public class InvalidGenderException extends DomainException {
    public InvalidGenderException() {
        super(DomainErrorCode.GENDER_INVALID);
    }
}
