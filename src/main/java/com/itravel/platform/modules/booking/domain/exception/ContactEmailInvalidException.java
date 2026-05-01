package com.itravel.platform.modules.booking.domain.exception;

import com.itravel.platform.common.exceptions.DomainErrorCode;
import com.itravel.platform.common.exceptions.DomainException;

public class ContactEmailInvalidException extends DomainException {
    public ContactEmailInvalidException() {
        super(DomainErrorCode.BOOKING_CONTACT_EMAIL_INVALID);
    }
}
