package com.itravel.platform.modules.identity.application.exception;

import com.itravel.platform.common.exceptions.ApplicationErrorCode;
import com.itravel.platform.common.exceptions.ApplicationException;

public class CustomerDeletedException extends ApplicationException {
    public CustomerDeletedException() {
        super(ApplicationErrorCode.CUSTOMER_DELETED);
    }
}
