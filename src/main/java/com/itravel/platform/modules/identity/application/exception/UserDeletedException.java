package com.itravel.platform.modules.identity.application.exception;

import com.itravel.platform.common.exceptions.ApplicationErrorCode;
import com.itravel.platform.common.exceptions.ApplicationException;

public class UserDeletedException extends ApplicationException {
    public UserDeletedException() {
        super(ApplicationErrorCode.USER_DELETED);
    }
}
