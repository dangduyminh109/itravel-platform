package com.itravel.platform.modules.identity.application.exception;

import com.itravel.platform.common.exceptions.ApplicationErrorCode;
import com.itravel.platform.common.exceptions.ApplicationException;

public class InvalidFirebaseTokenException extends ApplicationException {
    public InvalidFirebaseTokenException() {
        super(ApplicationErrorCode.INVALID_FIREBASE_TOKEN);
    }
}
