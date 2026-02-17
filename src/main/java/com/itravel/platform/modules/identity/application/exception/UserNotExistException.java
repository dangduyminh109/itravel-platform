package com.itravel.platform.modules.identity.application.exception;

import com.itravel.platform.common.exceptions.ApplicationErrorCode;
import com.itravel.platform.common.exceptions.ApplicationException;

public class UserNotExistException extends ApplicationException {
    public UserNotExistException() {
        super(ApplicationErrorCode.USER_NOT_EXIST);
    }
}
