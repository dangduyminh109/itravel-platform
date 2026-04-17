package com.itravel.platform.modules.identity.domain.account.exception;

import com.itravel.platform.common.exceptions.DomainErrorCode;
import com.itravel.platform.common.exceptions.DomainException;

public class InvalidUsernameException extends DomainException {
    public InvalidUsernameException() {
        super(DomainErrorCode.INVALID_USER_NAME);
    }
}

