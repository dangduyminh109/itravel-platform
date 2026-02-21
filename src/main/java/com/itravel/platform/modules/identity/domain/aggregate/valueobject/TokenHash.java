package com.itravel.platform.modules.identity.domain.aggregate.valueobject;

import com.itravel.platform.modules.identity.domain.exception.InvalidTokenException;

public record TokenHash(String value) {
    public TokenHash {
        if (value == null || value.isBlank()) {
            throw new InvalidTokenException();
        }
    }
}
