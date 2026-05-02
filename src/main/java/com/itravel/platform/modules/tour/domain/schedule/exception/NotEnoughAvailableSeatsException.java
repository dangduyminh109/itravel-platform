package com.itravel.platform.modules.tour.domain.schedule.exception;

import com.itravel.platform.common.exceptions.DomainErrorCode;
import com.itravel.platform.common.exceptions.DomainException;

public class NotEnoughAvailableSeatsException extends DomainException {
    public NotEnoughAvailableSeatsException() {
        super(DomainErrorCode.INVENTORY_UNAVAILABLE);
    }
}
