package com.itravel.platform.modules.identity.domain.account.exception;

import com.itravel.platform.common.exceptions.DomainErrorCode;
import com.itravel.platform.common.exceptions.DomainException;

public class InvalidPasswordForGoogleAccountException extends DomainException {
    public InvalidPasswordForGoogleAccountException() {
        super(DomainErrorCode.INVALID_PASSWORD_FOR_GOOGLE_ACCOUNT);
    }
}

