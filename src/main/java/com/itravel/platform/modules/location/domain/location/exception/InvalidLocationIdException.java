package com.itravel.platform.modules.location.domain.location.exception;

import com.itravel.platform.common.exceptions.DomainErrorCode;
import com.itravel.platform.common.exceptions.DomainException;

public class InvalidLocationIdException extends DomainException {
    public InvalidLocationIdException() {
        super(DomainErrorCode.INVALID_LOCATION_ID);
    }
}
