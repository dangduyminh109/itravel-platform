package com.itravel.platform.modules.identity.domain.aggregate.valueobject;

import com.itravel.platform.modules.identity.domain.exception.InvalidPhoneNumberException;

import java.util.regex.Pattern;

public record PhoneNumber(String value) {
    private static final Pattern PHONE_PATTERN = Pattern.compile("^\\+?[0-9]{10,15}$");

    public PhoneNumber {
        if (value != null && !value.isBlank() && !PHONE_PATTERN.matcher(value).matches()) {
            throw new InvalidPhoneNumberException();
        }
    }
}

