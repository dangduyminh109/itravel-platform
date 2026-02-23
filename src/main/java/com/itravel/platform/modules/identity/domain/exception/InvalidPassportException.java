package com.itravel.platform.modules.identity.domain.exception;

import com.itravel.platform.common.exceptions.DomainErrorCode;
import com.itravel.platform.common.exceptions.DomainException;

public class InvalidPassportException extends DomainException {
    public InvalidPassportException(DomainErrorCode errorCode) {
        super(errorCode);
    }
}

