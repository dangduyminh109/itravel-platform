package com.itravel.platform.modules.identity.application.exception;
import com.itravel.platform.common.exceptions.ApplicationErrorCode;
import com.itravel.platform.common.exceptions.ApplicationException;

public class PasswordConfirmationMismatchException extends ApplicationException {
    public PasswordConfirmationMismatchException() {
        super(ApplicationErrorCode.PASSWORD_CONFIRMATION_MISMATCH);
    }
}
