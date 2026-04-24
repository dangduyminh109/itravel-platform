package com.itravel.platform.modules.booking.domain.booking;

import com.itravel.platform.modules.booking.domain.exception.InvalidContactInfoException;
import java.util.Objects;
import java.util.regex.Pattern;

public record ContactInfo(String fullName, String email, String phone) {
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");

    public ContactInfo {
        if (Objects.isNull(fullName) || fullName.trim().isEmpty()) {
            throw new InvalidContactInfoException();
        }
        if (Objects.isNull(email) || !EMAIL_PATTERN.matcher(email).matches()) {
            throw new InvalidContactInfoException();
        }
        if (Objects.isNull(phone) || phone.length() < 9) {
            throw new InvalidContactInfoException();
        }
    }
}