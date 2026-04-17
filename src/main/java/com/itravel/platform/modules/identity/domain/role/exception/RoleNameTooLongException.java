package com.itravel.platform.modules.identity.domain.role.exception;

import com.itravel.platform.common.exceptions.DomainErrorCode;
import com.itravel.platform.common.exceptions.DomainException;

public class RoleNameTooLongException extends DomainException {
    public RoleNameTooLongException() {
        super(DomainErrorCode.ROLE_NAME_TOO_LONG);
    }
}

