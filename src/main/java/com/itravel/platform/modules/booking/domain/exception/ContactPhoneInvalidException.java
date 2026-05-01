package com.itravel.platform.modules.booking.domain.exception;

import com.itravel.platform.common.exceptions.DomainErrorCode;
import com.itravel.platform.common.exceptions.DomainException;

public class ContactPhoneInvalidException extends DomainException {
    public ContactPhoneInvalidException() {
        super(DomainErrorCode.BOOKING_CONTACT_PHONE_INVALID);
    }
}
