package com.itravel.platform.modules.identity.domain.exception;

import com.itravel.platform.common.exceptions.DomainErrorCode;
import com.itravel.platform.common.exceptions.DomainException;

public class InvalidAddressException extends DomainException {
    public InvalidAddressException(DomainErrorCode errorCode) {
        super(errorCode);
    }
}

