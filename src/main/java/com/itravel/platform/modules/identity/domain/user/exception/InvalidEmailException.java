package com.itravel.platform.modules.identity.domain.user.exception;

import com.itravel.platform.common.exceptions.DomainErrorCode;
import com.itravel.platform.common.exceptions.DomainException;

public class InvalidEmailException extends DomainException {
    public InvalidEmailException() {
        super(DomainErrorCode.INVALID_EMAIL);
    }
}

