package com.itravel.platform.modules.identity.application.exception;

import com.itravel.platform.common.exceptions.ApplicationErrorCode;
import com.itravel.platform.common.exceptions.ApplicationException;

public class AccountNotExistException extends ApplicationException {
    public AccountNotExistException() {
        super(ApplicationErrorCode.ACCOUNT_NOT_EXIST);
    }
}
