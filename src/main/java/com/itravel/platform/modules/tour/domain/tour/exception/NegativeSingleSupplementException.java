package com.itravel.platform.modules.tour.domain.tour.exception;

import com.itravel.platform.common.exceptions.DomainErrorCode;
import com.itravel.platform.common.exceptions.DomainException;

public class NegativeSingleSupplementException extends DomainException {
    public NegativeSingleSupplementException() {
        super(DomainErrorCode.NEGATIVE_SINGLE_SUPPLEMENT);
    }
}
