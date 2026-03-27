package com.itravel.platform.modules.location.application.exception;

import com.itravel.platform.common.exceptions.ApplicationErrorCode;
import com.itravel.platform.common.exceptions.ApplicationException;

public class LocationNameExistedException extends ApplicationException {
    public LocationNameExistedException() {
        super(ApplicationErrorCode.LOCATION_NAME_EXISTED);
    }
}
