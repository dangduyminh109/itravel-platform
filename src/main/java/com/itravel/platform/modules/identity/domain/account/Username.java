package com.itravel.platform.modules.identity.domain.account;

import com.itravel.platform.modules.identity.domain.account.exception.InvalidUsernameException;

public record Username(String value) {
     public Username {
        if (value == null || value.isBlank()) {
            throw new InvalidUsernameException();
        }
    }
}

