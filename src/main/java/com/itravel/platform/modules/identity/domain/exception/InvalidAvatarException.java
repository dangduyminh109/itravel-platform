package com.itravel.platform.modules.identity.domain.exception;

import com.itravel.platform.common.exceptions.DomainErrorCode;
import com.itravel.platform.common.exceptions.DomainException;

public class InvalidAvatarException extends DomainException {
    public InvalidAvatarException() {
        super(DomainErrorCode.INVALID_AVATAR);
    }
}

