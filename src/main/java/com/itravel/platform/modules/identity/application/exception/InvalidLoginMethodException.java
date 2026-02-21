package com.itravel.platform.modules.identity.application.exception;

import com.itravel.platform.common.exceptions.ApplicationErrorCode;
import com.itravel.platform.common.exceptions.ApplicationException;

public class InvalidLoginMethodException extends ApplicationException {
    public InvalidLoginMethodException() {
        super(ApplicationErrorCode.INVALID_LOGIN_METHOD);
    }
}
