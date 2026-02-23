package com.itravel.platform.modules.identity.domain.exception;

import com.itravel.platform.common.exceptions.DomainErrorCode;
import com.itravel.platform.common.exceptions.DomainException;

public class InvalidIdentityCardException extends DomainException {
    public InvalidIdentityCardException(DomainErrorCode errorCode) {
        super(errorCode);
    }
}

