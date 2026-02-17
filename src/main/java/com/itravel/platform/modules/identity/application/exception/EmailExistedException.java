package com.itravel.platform.modules.identity.application.exception;
import com.itravel.platform.common.exceptions.ApplicationErrorCode;
import com.itravel.platform.common.exceptions.ApplicationException;

public class EmailExistedException extends ApplicationException {
    public EmailExistedException() {
        super(ApplicationErrorCode.EMAIL_EXISTED);
    }
}
