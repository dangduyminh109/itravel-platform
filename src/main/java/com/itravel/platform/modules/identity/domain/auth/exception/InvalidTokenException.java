package com.itravel.platform.modules.identity.domain.auth.exception;

import com.itravel.platform.common.exceptions.DomainErrorCode;
import com.itravel.platform.common.exceptions.DomainException;

public class InvalidTokenException extends DomainException {
    public InvalidTokenException() {
        super(DomainErrorCode.INVALID_TOKEN);
    }
}

