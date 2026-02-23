package com.itravel.platform.common.exceptions;

public class FirebaseException extends AppException {
    public FirebaseException(ErrorCode errorCode) {
        super(errorCode);
    }
}

