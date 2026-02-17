package com.itravel.platform.modules.identity.domain.exception;

import com.itravel.platform.common.exceptions.DomainErrorCode;
import com.itravel.platform.common.exceptions.DomainException;

public class AdminRoleImmutableException extends DomainException {
    public AdminRoleImmutableException(){
        super(DomainErrorCode.TOKEN_NOT_OWNED);
    }
}
