package com.itravel.platform.modules.identity.domain.exception;

import com.itravel.platform.common.exceptions.DomainErrorCode;
import com.itravel.platform.common.exceptions.DomainException;

public class EmailCredentialsRequiredException extends DomainException {
    public EmailCredentialsRequiredException() {
        super(DomainErrorCode.EMAIL_CREDENTIALS_REQUIRED);
    }
}
