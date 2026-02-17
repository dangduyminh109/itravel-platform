package com.itravel.platform.modules.identity.domain.exception;

import com.itravel.platform.common.exceptions.DomainErrorCode;
import com.itravel.platform.common.exceptions.DomainException;

public class InvalidPermissionCodeException extends DomainException {
    public InvalidPermissionCodeException() {
        super(DomainErrorCode.INVALID_PERMISSION_CODE);
    }
}
