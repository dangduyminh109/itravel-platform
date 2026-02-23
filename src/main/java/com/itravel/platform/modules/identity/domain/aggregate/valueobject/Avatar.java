package com.itravel.platform.modules.identity.domain.aggregate.valueobject;

import com.itravel.platform.modules.identity.domain.exception.InvalidAvatarException;

import java.util.regex.Pattern;

public record Avatar(String value) {
    public Avatar {
        if (value == null || value.isBlank()) {
            throw new InvalidAvatarException();
        }
    }
}

