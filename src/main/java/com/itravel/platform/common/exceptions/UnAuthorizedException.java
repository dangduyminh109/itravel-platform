package com.itravel.platform.common.exceptions;

public class UnAuthorizedException extends AppException {
    public UnAuthorizedException() {
        super(ErrorCode.UNAUTHORIZED);
    }
}
