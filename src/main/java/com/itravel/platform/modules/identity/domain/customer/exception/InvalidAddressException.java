package com.itravel.platform.modules.identity.domain.customer.exception;

import com.itravel.platform.common.exceptions.DomainErrorCode;
import com.itravel.platform.common.exceptions.DomainException;

public class InvalidAddressException extends DomainException {
    public InvalidAddressException(DomainErrorCode errorCode) {
        super(errorCode);
    }
}


