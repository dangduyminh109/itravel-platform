package com.itravel.platform.modules.identity.application.exception;

import com.itravel.platform.common.exceptions.ApplicationErrorCode;
import com.itravel.platform.common.exceptions.ApplicationException;

public class UsernameOrPasswordInvalidException extends ApplicationException {
    public UsernameOrPasswordInvalidException() {
        super(ApplicationErrorCode.USERNAME_OR_PASSWORD_INVALID);
    }
}
