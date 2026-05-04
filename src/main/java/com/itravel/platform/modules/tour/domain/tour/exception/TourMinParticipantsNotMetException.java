package com.itravel.platform.modules.tour.domain.tour.exception;

import com.itravel.platform.common.exceptions.DomainErrorCode;
import com.itravel.platform.common.exceptions.DomainException;

public class TourMinParticipantsNotMetException extends DomainException {
    public TourMinParticipantsNotMetException() {
        super(DomainErrorCode.TOUR_MIN_PARTICIPANTS_NOT_MET);
    }
}
