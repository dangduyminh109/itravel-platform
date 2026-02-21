package com.itravel.platform.modules.identity.application.exception;

import com.itravel.platform.common.exceptions.ApplicationErrorCode;
import com.itravel.platform.common.exceptions.ApplicationException;

public class RefreshTokenRevokedException extends ApplicationException {
    public RefreshTokenRevokedException() {
        super(ApplicationErrorCode.REFRESH_TOKEN_REVOKED);
    }
}
