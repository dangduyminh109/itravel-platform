package com.itravel.platform.modules.identity.domain.user;

import com.itravel.platform.modules.identity.domain.user.exception.InvalidFullNameException;

public record FullName(String value) {
    public FullName {
        if (value == null || value.isBlank()) {
            throw new InvalidFullNameException();
        }
    }
}

