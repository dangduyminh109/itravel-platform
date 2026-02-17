package com.itravel.platform.modules.identity.infrastructure.exception;

import com.itravel.platform.common.exceptions.AppException;
import com.itravel.platform.common.exceptions.DomainErrorCode;
import com.itravel.platform.common.exceptions.DomainException;
import com.itravel.platform.common.exceptions.ErrorCode;

public class InvalidVerificationTokenException extends AppException {
    public InvalidVerificationTokenException() {
        super(ErrorCode.INVALID_TOKEN);
    }
}
