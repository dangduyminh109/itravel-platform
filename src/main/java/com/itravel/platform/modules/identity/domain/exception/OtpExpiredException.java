package com.itravel.platform.modules.identity.domain.exception;

import com.itravel.platform.common.exceptions.DomainErrorCode;
import com.itravel.platform.common.exceptions.DomainException;

public class OtpExpiredException extends DomainException {
    public OtpExpiredException() {
        super(DomainErrorCode.OTP_EXPIRED);
    }
}
