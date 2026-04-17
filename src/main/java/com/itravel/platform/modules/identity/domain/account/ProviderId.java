package com.itravel.platform.modules.identity.domain.account;

import com.itravel.platform.modules.identity.domain.account.exception.InvalidProviderIdException;

public record ProviderId(String value) {
    public ProviderId{
        if (value == null || value.isBlank()) {
            throw new InvalidProviderIdException();
        }
    }
}

