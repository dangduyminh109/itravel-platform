package com.itravel.platform.modules.identity.application.exception;

import com.itravel.platform.common.exceptions.ApplicationErrorCode;
import com.itravel.platform.common.exceptions.ApplicationException;

public class AccountLinkNotExistException extends ApplicationException {
    public AccountLinkNotExistException() {
        super(ApplicationErrorCode.ACCOUNT_LINK_NOT_EXIST);
    }
}
