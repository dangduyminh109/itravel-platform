package com.itravel.platform.modules.identity.infrastructure.persistence.mapper;

import com.itravel.platform.modules.identity.domain.auth.RefreshToken;
import com.itravel.platform.modules.identity.domain.account.AccountId;
import com.itravel.platform.modules.identity.domain.auth.TokenHash;
import com.itravel.platform.modules.identity.domain.auth.TokenId;
import com.itravel.platform.modules.identity.infrastructure.persistence.entity.RefreshTokenJpaEntity;
import com.itravel.platform.modules.identity.share.IdentityValueObjectMapper;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        uses = {IdentityValueObjectMapper.class},
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface RefreshTokenMapper {
    RefreshTokenJpaEntity toRefreshTokenJpaEntity(RefreshToken refreshToken);

    static RefreshToken toRefreshTokenDomain(RefreshTokenJpaEntity entity) {
        return RefreshToken.fromExistingBuilder()
                .id(new TokenId(entity.getId()))
                .accountId(new AccountId(entity.getAccountId()))
                .tokenHash(new TokenHash(entity.getTokenHash()))
                .createdAt(  entity.getCreatedAt())
                .revokedAt(entity.getRevokedAt())
                .expiresAt(entity.getExpiresAt())
                .build();
    }
}
