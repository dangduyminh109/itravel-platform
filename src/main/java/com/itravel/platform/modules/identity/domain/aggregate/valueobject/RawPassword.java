package com.itravel.platform.modules.identity.domain.aggregate.valueobject;

import com.itravel.platform.modules.identity.domain.exception.InvalidPasswordException;
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
