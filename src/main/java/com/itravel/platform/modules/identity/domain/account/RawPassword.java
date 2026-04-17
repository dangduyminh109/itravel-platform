package com.itravel.platform.modules.identity.domain.account;

import com.itravel.platform.modules.identity.domain.account.exception.InvalidPasswordException;
import java.util.regex.Pattern;

public record RawPassword(String value) {

    private static final Pattern STRONG_PASSWORD_PATTERN = Pattern.compile(
            "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$"
    );

    public RawPassword {
        if (value == null || value.isBlank()) {
            throw new InvalidPasswordException();
        }
        if (!STRONG_PASSWORD_PATTERN.matcher(value).matches()) {
            throw new InvalidPasswordException();
        }
    }
}

