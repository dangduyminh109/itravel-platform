package com.itravel.platform.common.exceptions;

public class UploadFailedException extends AppException {
    public UploadFailedException() {
        super(ErrorCode.UPLOAD_FAILED);
    }
}

