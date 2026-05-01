package com.itravel.platform.modules.booking.domain.exception;

import com.itravel.platform.common.exceptions.DomainErrorCode;
import com.itravel.platform.common.exceptions.DomainException;

public class ContactFullNameRequiredException extends DomainException {
    public ContactFullNameRequiredException() {
        super(DomainErrorCode.BOOKING_CONTACT_FULLNAME_REQUIRED);
    }
}
