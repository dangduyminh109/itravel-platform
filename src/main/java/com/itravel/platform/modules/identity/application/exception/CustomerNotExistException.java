package com.itravel.platform.modules.identity.application.exception;

import com.itravel.platform.common.exceptions.ApplicationErrorCode;
import com.itravel.platform.common.exceptions.ApplicationException;

public class CustomerNotExistException extends ApplicationException {
    public CustomerNotExistException() {
        super(ApplicationErrorCode.CUSTOMER_NOT_EXIST);
    }
}
