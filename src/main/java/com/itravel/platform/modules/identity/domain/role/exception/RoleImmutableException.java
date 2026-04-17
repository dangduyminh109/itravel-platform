package com.itravel.platform.modules.identity.domain.role.exception;

import com.itravel.platform.common.exceptions.DomainErrorCode;
import com.itravel.platform.common.exceptions.DomainException;

public class RoleImmutableException extends DomainException {
    public RoleImmutableException(){
        super(DomainErrorCode.ROLE_IMMUTABLE);
    }
}

