package com.itravel.platform.modules.identity.domain.exception;

import com.itravel.platform.common.exceptions.DomainErrorCode;
import com.itravel.platform.common.exceptions.DomainException;

public class UsernameCredentialsRequiredException extends DomainException {
    public UsernameCredentialsRequiredException() {
        super(DomainErrorCode.USERNAME_CREDENTIALS_REQUIRED);
    }
}
