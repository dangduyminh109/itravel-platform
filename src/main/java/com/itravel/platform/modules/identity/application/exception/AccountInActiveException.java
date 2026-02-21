package com.itravel.platform.modules.identity.application.exception;

import com.itravel.platform.common.exceptions.ApplicationErrorCode;
import com.itravel.platform.common.exceptions.ApplicationException;

public class AccountInActiveException extends ApplicationException {
    public AccountInActiveException() {
        super(ApplicationErrorCode.ACCOUNT_INACTIVE);
    }
}
