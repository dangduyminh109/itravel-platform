package com.itravel.platform.modules.location.domain.location.exception;

import com.itravel.platform.common.exceptions.DomainErrorCode;
import com.itravel.platform.common.exceptions.DomainException;

public class InvalidTypeOrParentException extends DomainException {
    public InvalidTypeOrParentException() {
        super(DomainErrorCode.INVALID_TYPE_OR_PARENT);
    }
}
