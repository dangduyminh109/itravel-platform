package com.itravel.platform.modules.identity.domain.account.exception;

import com.itravel.platform.common.exceptions.DomainErrorCode;
import com.itravel.platform.common.exceptions.DomainException;

public class GoogleCredentialsRequiredException extends DomainException {
    public GoogleCredentialsRequiredException() {
        super(DomainErrorCode.GOOGLE_CREDENTIALS_REQUIRED);
    }
}

