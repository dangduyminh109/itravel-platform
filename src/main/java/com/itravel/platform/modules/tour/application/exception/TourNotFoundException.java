package com.itravel.platform.modules.tour.application.exception;

import com.itravel.platform.common.exceptions.ApplicationErrorCode;
import com.itravel.platform.common.exceptions.ApplicationException;

public class TourNotFoundException extends ApplicationException {
    public TourNotFoundException() {
        super(ApplicationErrorCode.TOUR_NOT_EXIST);
    }
}

