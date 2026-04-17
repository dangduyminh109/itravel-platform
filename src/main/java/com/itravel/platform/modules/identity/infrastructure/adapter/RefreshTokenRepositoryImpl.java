package com.itravel.platform.modules.identity.infrastructure.adapter;

import com.itravel.platform.modules.identity.domain.auth.RefreshToken;
import com.itravel.platform.modules.identity.domain.auth.TokenHash;
import com.itravel.platform.modules.identity.application.port.out.auth.RefreshTokenRepository;
import com.itravel.platform.modules.identity.infrastructure.persistence.entity.RefreshTokenJpaEntity;
import com.itravel.platform.modules.identity.infrastructure.persistence.mapper.RefreshTokenMapper;
import com.itravel.platform.modules.identity.infrastructure.persistence.repository.RefreshTokenJpaRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
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
