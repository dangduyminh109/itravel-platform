package com.itravel.platform.modules.identity.infrastructure.security;

import com.itravel.platform.modules.identity.domain.service.RefreshTokenGenerator;
import org.springframework.stereotype.Component;
import java.security.SecureRandom;
import java.util.Base64;

@Component
public class RandomRefreshTokenGenerator implements RefreshTokenGenerator {
    private static final int TOKEN_LENGTH = 64;
    private final SecureRandom secureRandom = new SecureRandom();

    @Override
    public String generate() {
        byte[] randomBytes = new byte[TOKEN_LENGTH];
        secureRandom.nextBytes(randomBytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(randomBytes);
    }
}
