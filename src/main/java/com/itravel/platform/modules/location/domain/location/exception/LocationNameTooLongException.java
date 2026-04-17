package com.itravel.platform.modules.location.domain.location.exception;

import com.itravel.platform.common.exceptions.DomainErrorCode;
import com.itravel.platform.common.exceptions.DomainException;

public class LocationNameTooLongException extends DomainException {
    public LocationNameTooLongException() {
        super(DomainErrorCode.LOCATION_NAME_TOO_LONG);
    }
}
