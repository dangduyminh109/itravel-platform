package com.itravel.platform.modules.tour.application.exception;

import com.itravel.platform.common.exceptions.ApplicationErrorCode;
import com.itravel.platform.common.exceptions.ApplicationException;

public class ScheduleNotFoundException extends ApplicationException {
    public ScheduleNotFoundException() {
        super(ApplicationErrorCode.SCHEDULE_NOT_EXIST);
    }
}
