package com.itravel.platform.modules.identity.domain.role.exception;

import com.itravel.platform.common.exceptions.DomainErrorCode;
import com.itravel.platform.common.exceptions.DomainException;

public class InvalidRoleNameException extends DomainException {
    public InvalidRoleNameException() {
        super(DomainErrorCode.INVALID_ROLE_NAME);
    }
}

