package com.itravel.platform.modules.identity.domain.service;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.PasswordHash;

public interface PasswordEncoder {
    PasswordHash encode(String rawPassword);
    boolean matches(String rawPassword, PasswordHash hash);
}
