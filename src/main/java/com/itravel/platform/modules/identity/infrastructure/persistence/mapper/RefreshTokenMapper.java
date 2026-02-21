package com.itravel.platform.modules.identity.infrastructure.persistence.mapper;

import com.itravel.platform.modules.identity.domain.aggregate.RefreshToken;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.AccountId;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.TokenHash;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.TokenId;
import com.itravel.platform.modules.identity.infrastructure.persistence.entity.RefreshTokenJpaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface RefreshTokenMapper {

    @Mapping(target = "accountId", expression = "java(refreshToken.getAccountId() != null ? refreshToken.getAccountId().value() : null)")
    @Mapping(target = "id", expression = "java(refreshToken.getId() != null ? refreshToken.getId().value() : null)")
    @Mapping(target = "tokenHash", expression = "java(refreshToken.getTokenHash() != null ? refreshToken.getTokenHash().value() : null)")
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
