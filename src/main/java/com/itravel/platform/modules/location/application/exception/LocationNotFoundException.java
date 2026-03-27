package com.itravel.platform.modules.location.application.exception;

import com.itravel.platform.common.exceptions.ApplicationErrorCode;
import com.itravel.platform.common.exceptions.ApplicationException;

public class LocationNotFoundException extends ApplicationException {
    public LocationNotFoundException() {
        super(ApplicationErrorCode.LOCATION_NOT_EXITS);
    }
}
