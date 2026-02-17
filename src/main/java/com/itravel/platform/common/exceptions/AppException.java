package com.itravel.platform.common.exceptions;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class AppException extends RuntimeException {
    protected ErrorCode errorCode;

    protected AppException(ErrorCode errorCode) {
        super(errorCode.message);
        this.errorCode = errorCode;
    }
}
