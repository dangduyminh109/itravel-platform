package com.itravel.platform.common.exceptions;

public class InvalidSlugException extends DomainException {
    public InvalidSlugException() {
        super(DomainErrorCode.INVALID_SLUG);
    }
}
