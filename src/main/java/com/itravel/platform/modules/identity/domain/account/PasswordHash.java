package com.itravel.platform.modules.identity.domain.account;

import com.itravel.platform.modules.identity.domain.account.exception.InvalidPasswordException;

public record PasswordHash(String value) {
    public PasswordHash {
        if (value == null || value.isBlank()) {
            throw new InvalidPasswordException();
        }
    }
}

