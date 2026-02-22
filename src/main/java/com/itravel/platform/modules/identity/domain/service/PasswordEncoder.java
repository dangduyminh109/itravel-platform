package com.itravel.platform.modules.identity.domain.service;

import com.itravel.platform.modules.identity.domain.aggregate.valueobject.PasswordHash;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.RawPassword;

public interface PasswordEncoder {
    PasswordHash encode(RawPassword rawPassword);
    boolean matches(RawPassword rawPassword, PasswordHash hash);
}
