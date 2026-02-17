package com.itravel.platform.modules.identity.domain.exception;

import com.itravel.platform.common.exceptions.DomainErrorCode;
import com.itravel.platform.common.exceptions.DomainException;

public class InvalidPasswordException extends DomainException {
    public InvalidPasswordException() {
        super(DomainErrorCode.INVALID_PASSWORD);
    }
}
