package com.itravel.platform.modules.identity.domain.auth.exception;

import com.itravel.platform.common.exceptions.DomainErrorCode;
import com.itravel.platform.common.exceptions.DomainException;

public class TokenNotOwnedException extends DomainException {
    public TokenNotOwnedException() {
        super(DomainErrorCode.TOKEN_NOT_OWNED);
    }
}

