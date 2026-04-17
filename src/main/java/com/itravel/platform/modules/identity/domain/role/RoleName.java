package com.itravel.platform.modules.identity.domain.role;

import com.itravel.platform.modules.identity.domain.role.exception.InvalidRoleNameException;
import com.itravel.platform.modules.identity.domain.role.exception.RoleNameTooLongException;
import java.util.Objects;

public record RoleName(String value) {
    public RoleName {
        if(Objects.isNull(value) || value.isBlank()){
            throw new InvalidRoleNameException();
        }
        if (value.length() > 50){
            throw new RoleNameTooLongException();
        }
    }
}

