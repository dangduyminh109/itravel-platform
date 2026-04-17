package com.itravel.platform.modules.identity.application.port.out.auth;

import com.itravel.platform.modules.identity.domain.auth.RefreshToken;
import com.itravel.platform.modules.identity.domain.auth.TokenHash;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface RefreshTokenRepository {
    void save(RefreshToken refreshToken);
    Optional<RefreshToken> findByTokenHash(TokenHash tokenHash);
}

