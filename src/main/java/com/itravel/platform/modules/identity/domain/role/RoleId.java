package com.itravel.platform.modules.identity.domain.role;

import com.itravel.platform.modules.identity.domain.role.exception.InvalidRoleIdException;
import java.util.Objects;

public record RoleId(Long value) {
    public RoleId{
        if(Objects.isNull(value)){
            throw new InvalidRoleIdException();
        }
    }
}

