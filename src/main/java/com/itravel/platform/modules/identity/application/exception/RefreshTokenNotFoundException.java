package com.itravel.platform.modules.identity.application.exception;

import com.itravel.platform.common.exceptions.ApplicationErrorCode;
import com.itravel.platform.common.exceptions.ApplicationException;

public class RefreshTokenNotFoundException extends ApplicationException {
    public RefreshTokenNotFoundException() {
        super(ApplicationErrorCode.REFRESH_TOKEN_NOT_FOUND);
    }
}
