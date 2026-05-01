package com.itravel.platform.modules.booking.domain.booking;

import com.itravel.platform.modules.booking.domain.exception.ContactEmailInvalidException;
import com.itravel.platform.modules.booking.domain.exception.ContactFullNameRequiredException;
import com.itravel.platform.modules.booking.domain.exception.ContactPhoneInvalidException;

import java.util.regex.Pattern;

public record ContactInfo(String fullName, String email, String phone, String address) {
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");

    public ContactInfo {
        if (fullName == null || fullName.isBlank()) {
            throw new ContactFullNameRequiredException();
        }
        if (email == null || !EMAIL_PATTERN.matcher(email).matches()) {
            throw new ContactEmailInvalidException();
        }
        if (phone == null || phone.length() < 9) {
            throw new ContactPhoneInvalidException();
        }
    }
}