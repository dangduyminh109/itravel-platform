package com.itravel.platform.modules.identity.domain.repository;

import com.itravel.platform.modules.identity.domain.aggregate.RefreshToken;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.TokenHash;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface RefreshTokenRepository {
    void save(RefreshToken refreshToken);
    Optional<RefreshToken> findByTokenHash(TokenHash tokenHash);
}
