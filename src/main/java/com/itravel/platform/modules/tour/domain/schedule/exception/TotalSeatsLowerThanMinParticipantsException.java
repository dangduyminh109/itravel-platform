package com.itravel.platform.modules.tour.domain.schedule.exception;

import com.itravel.platform.common.exceptions.DomainErrorCode;
import com.itravel.platform.common.exceptions.DomainException;

public class TotalSeatsLowerThanMinParticipantsException extends DomainException {
    public TotalSeatsLowerThanMinParticipantsException() {
        super(DomainErrorCode.TOTAL_SEATS_LOWER_THAN_MIN_PARTICIPANTS);
    }
}
