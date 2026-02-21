package com.itravel.platform.modules.identity.application.exception;

import com.itravel.platform.common.exceptions.ApplicationErrorCode;
import com.itravel.platform.common.exceptions.ApplicationException;

public class AccountDeletedException extends ApplicationException {
    public AccountDeletedException() {
        super(ApplicationErrorCode.ACCOUNT_DELETED);
    }
}
