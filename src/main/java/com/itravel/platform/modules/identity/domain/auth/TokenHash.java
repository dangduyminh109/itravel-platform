package com.itravel.platform.modules.identity.domain.auth;

import com.itravel.platform.modules.identity.domain.auth.exception.InvalidTokenException;

public record TokenHash(String value) {
    public TokenHash {
        if (value == null || value.isBlank()) {
            throw new InvalidTokenException();
        }
    }
}

