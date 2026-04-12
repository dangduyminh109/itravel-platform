package com.itravel.platform.modules.tour.application.exception;

import com.itravel.platform.common.exceptions.ApplicationErrorCode;
import com.itravel.platform.common.exceptions.ApplicationException;

public class TourNameExistedException extends ApplicationException {
    public TourNameExistedException() {
        super(ApplicationErrorCode.TOUR_NAME_EXISTED);
    }
}

