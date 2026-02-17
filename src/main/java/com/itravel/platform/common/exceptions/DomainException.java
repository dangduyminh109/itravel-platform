package com.itravel.platform.common.exceptions;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class DomainException extends RuntimeException {
    protected DomainErrorCode domainErrorCode;

    protected DomainException(DomainErrorCode errorCode) {
        super(errorCode.message);
        this.domainErrorCode = errorCode;
    }
}
