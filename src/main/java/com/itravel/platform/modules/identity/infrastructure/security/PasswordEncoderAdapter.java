package com.itravel.platform.modules.identity.infrastructure.security;

import com.itravel.platform.modules.identity.domain.aggregate.valueobject.PasswordHash;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.RawPassword;
import com.itravel.platform.modules.identity.domain.service.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordEncoderAdapter implements PasswordEncoder {
    private final BCryptPasswordEncoder delegate = new BCryptPasswordEncoder();

    @Override
    public PasswordHash encode(RawPassword rawPassword) {
        return new PasswordHash(delegate.encode(rawPassword.value()));
    }

    @Override
    public boolean matches(RawPassword rawPassword, PasswordHash hash) {
        return delegate.matches(rawPassword.value(), hash.value());
    }
}
