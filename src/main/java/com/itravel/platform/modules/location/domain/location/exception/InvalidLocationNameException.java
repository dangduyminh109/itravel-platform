package com.itravel.platform.modules.location.domain.location.exception;

import com.itravel.platform.common.exceptions.DomainErrorCode;
import com.itravel.platform.common.exceptions.DomainException;

public class InvalidLocationNameException extends DomainException {
    public InvalidLocationNameException() {
        super(DomainErrorCode.INVALID_LOCATION_NAME);
    }
}
