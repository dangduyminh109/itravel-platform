package com.itravel.platform.modules.identity.domain.user.exception;

import com.itravel.platform.common.exceptions.DomainErrorCode;
import com.itravel.platform.common.exceptions.DomainException;

public class InvalidPhoneNumberException extends DomainException {
    public InvalidPhoneNumberException() {
        super(DomainErrorCode.INVALID_PHONE_NUMBER);
    }
}


