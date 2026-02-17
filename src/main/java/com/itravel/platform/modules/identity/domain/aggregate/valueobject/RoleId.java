package com.itravel.platform.modules.identity.domain.aggregate.valueobject;

import com.itravel.platform.modules.identity.domain.exception.InvalidRoleIdException;
import java.util.Objects;

public record RoleId(Long value) {
    public RoleId{
        if(Objects.isNull(value)){
            throw new InvalidRoleIdException();
        }
    }
}
