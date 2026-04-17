package com.itravel.platform.modules.identity.domain.user.exception;

import com.itravel.platform.common.exceptions.DomainErrorCode;
import com.itravel.platform.common.exceptions.DomainException;

public class InvalidFullNameException extends DomainException {
    public InvalidFullNameException() {
        super(DomainErrorCode.INVALID_FULL_NAME);
    }
}

