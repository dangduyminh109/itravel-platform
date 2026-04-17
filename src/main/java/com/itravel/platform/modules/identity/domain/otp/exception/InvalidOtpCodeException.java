package com.itravel.platform.modules.identity.domain.otp.exception;

import com.itravel.platform.common.exceptions.DomainErrorCode;
import com.itravel.platform.common.exceptions.DomainException;

public class InvalidOtpCodeException extends DomainException {
    public InvalidOtpCodeException() {
        super(DomainErrorCode.INVALID_OTP_CODE);
    }
}

