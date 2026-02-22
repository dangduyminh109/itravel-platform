package com.itravel.platform.common.exceptions;

public class UnAuthenticatedException extends AppException {
    public UnAuthenticatedException() {
        super(ErrorCode.UNAUTHENTICATED);
    }
}
