package com.itravel.platform.modules.identity.infrastructure.persistence.repository;

import com.itravel.platform.modules.identity.domain.aggregate.RefreshToken;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.TokenHash;
import com.itravel.platform.modules.identity.domain.repository.RefreshTokenRepository;
import com.itravel.platform.modules.identity.infrastructure.persistence.entity.RefreshTokenJpaEntity;
import com.itravel.platform.modules.identity.infrastructure.persistence.mapper.RefreshTokenMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class RefreshTokenRepositoryImpl implements RefreshTokenRepository {
    RefreshTokenJpaRepository refreshTokenJpaRepository;
    RefreshTokenMapper mapper;

    @Override
    public void save(RefreshToken refreshToken) {
        RefreshTokenJpaEntity refreshTokenJpaEntity = mapper.toRefreshTokenJpaEntity(refreshToken);
        refreshTokenJpaRepository.save(refreshTokenJpaEntity);
    }

    @Override
    public Optional<RefreshToken> findByTokenHash(TokenHash tokenHash) {
        RefreshTokenJpaEntity refreshTokenJpaEntity = refreshTokenJpaRepository
                .findByTokenHash(tokenHash.value());
        return Optional.ofNullable(RefreshTokenMapper.toRefreshTokenDomain(refreshTokenJpaEntity));
    }
}
