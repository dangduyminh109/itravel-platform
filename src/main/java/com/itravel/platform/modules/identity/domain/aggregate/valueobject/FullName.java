package com.itravel.platform.modules.identity.domain.aggregate.valueobject;

import com.itravel.platform.modules.identity.domain.exception.InvalidFullNameException;

public record FullName(String value) {
    public FullName {
        if (value == null || value.isBlank()) {
            throw new InvalidFullNameException();
        }
    }
}
