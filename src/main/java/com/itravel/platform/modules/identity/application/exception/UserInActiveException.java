package com.itravel.platform.modules.identity.application.exception;
import com.itravel.platform.common.exceptions.ApplicationErrorCode;
import com.itravel.platform.common.exceptions.ApplicationException;

public class UserInActiveException extends ApplicationException {
    public UserInActiveException() {
        super(ApplicationErrorCode.USER_INACTIVE);
    }
}
