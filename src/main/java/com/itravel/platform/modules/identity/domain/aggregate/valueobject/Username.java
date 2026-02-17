package com.itravel.platform.modules.identity.domain.aggregate.valueobject;

import com.itravel.platform.modules.identity.domain.exception.InvalidUsernameException;

public record Username(String value) {
     public Username {
        if (value == null || value.isBlank()) {
            throw new InvalidUsernameException();
        }
    }
}
