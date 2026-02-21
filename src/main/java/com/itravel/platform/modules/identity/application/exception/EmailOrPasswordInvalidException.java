package com.itravel.platform.modules.identity.application.exception;

import com.itravel.platform.common.exceptions.ApplicationErrorCode;
import com.itravel.platform.common.exceptions.ApplicationException;

public class EmailOrPasswordInvalidException extends ApplicationException {
    public EmailOrPasswordInvalidException() {
        super(ApplicationErrorCode.EMAIL_OR_PASSWORD_INVALID);
    }
}