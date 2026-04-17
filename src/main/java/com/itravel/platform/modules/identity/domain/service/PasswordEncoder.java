package com.itravel.platform.modules.identity.domain.service;

import com.itravel.platform.modules.identity.domain.account.PasswordHash;
import com.itravel.platform.modules.identity.domain.account.RawPassword;

public interface PasswordEncoder {
    PasswordHash encode(RawPassword rawPassword);
    boolean matches(RawPassword rawPassword, PasswordHash hash);
}
