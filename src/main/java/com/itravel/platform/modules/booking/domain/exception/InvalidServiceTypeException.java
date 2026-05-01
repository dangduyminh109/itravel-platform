package com.itravel.platform.modules.booking.domain.exception;

import com.itravel.platform.common.exceptions.DomainErrorCode;
import com.itravel.platform.common.exceptions.DomainException;

public class InvalidServiceTypeException extends DomainException {
    public InvalidServiceTypeException() {
        super(DomainErrorCode.INVALID_SERVICE_TYPE);
    }
}
