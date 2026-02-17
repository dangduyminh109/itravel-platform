package com.itravel.platform.modules.identity.application.exception;
import com.itravel.platform.common.exceptions.ApplicationErrorCode;
import com.itravel.platform.common.exceptions.ApplicationException;

public class UsernameExistedException extends ApplicationException {
    public UsernameExistedException() {
        super(ApplicationErrorCode.USERNAME_EXISTED);
    }
}
