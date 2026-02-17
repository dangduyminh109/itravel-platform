package com.itravel.platform.modules.identity.application.exception;

import com.itravel.platform.common.exceptions.ApplicationErrorCode;
import com.itravel.platform.common.exceptions.ApplicationException;

public class UserNotDeleteOrUpdateException extends ApplicationException {
    public UserNotDeleteOrUpdateException() {
        super(ApplicationErrorCode.USER_NOT_DELETE_OR_UPDATE);
    }
}
