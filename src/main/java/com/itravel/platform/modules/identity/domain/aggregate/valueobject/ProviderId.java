package com.itravel.platform.modules.identity.domain.aggregate.valueobject;

import com.itravel.platform.modules.identity.domain.exception.InvalidProviderIdException;

public record ProviderId(String value) {
    public ProviderId{
        if (value == null || value.isBlank()) {
            throw new InvalidProviderIdException();
        }
    }
}
