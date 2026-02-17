package com.itravel.platform.common.exceptions;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class ApplicationException extends RuntimeException {
    protected ApplicationErrorCode applicationErrorCode;

    protected ApplicationException(ApplicationErrorCode errorCode) {
        super(errorCode.message);
        this.applicationErrorCode = errorCode;
    }
}
