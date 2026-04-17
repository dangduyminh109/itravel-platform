package com.itravel.platform.modules.tour.domain.tour.exception;

import com.itravel.platform.common.exceptions.DomainErrorCode;
import com.itravel.platform.common.exceptions.DomainException;

public class ServicesOverlapException extends DomainException {
    public ServicesOverlapException() {
        super(DomainErrorCode.SERVICES_OVERLAP);
    }
}