package com.itravel.platform.modules.identity.domain.user;

import com.itravel.platform.modules.identity.domain.user.exception.InvalidEmailException;

import java.util.regex.Pattern;

public record Email(String value) {
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");

    public Email {
        if(value == null || !EMAIL_PATTERN.matcher(value).matches()){
            throw new InvalidEmailException();
        }
    }
}

