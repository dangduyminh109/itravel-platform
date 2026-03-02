package com.itravel.platform.modules.identity.domain.exception;
import com.itravel.platform.common.exceptions.DomainErrorCode;
import com.itravel.platform.common.exceptions.DomainException;

public class InvalidProviderIdException extends DomainException {
    public InvalidProviderIdException() {
        super(DomainErrorCode.INVALID_PROVIDER_ID);
    }
}
