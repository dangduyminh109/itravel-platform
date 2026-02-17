package com.itravel.platform.modules.identity.domain.aggregate.valueobject;
import com.itravel.platform.modules.identity.domain.exception.InvalidPermissionCodeException;

import java.util.Objects;

public record Permission(String code) {
    public Permission {
        if(Objects.isNull(code) || code.isBlank()){
            throw new InvalidPermissionCodeException();
        }
        code = code.trim().toUpperCase();
    }
}
