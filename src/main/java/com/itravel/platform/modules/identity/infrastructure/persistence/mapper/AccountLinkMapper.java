package com.itravel.platform.modules.identity.infrastructure.persistence.mapper;

import com.itravel.platform.modules.identity.domain.aggregate.AccountLink;
import com.itravel.platform.modules.identity.domain.aggregate.enums.AccountLinkType;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.AccountId;
import com.itravel.platform.modules.identity.infrastructure.persistence.entity.AccountLinkJpaEntity;
import com.itravel.platform.modules.identity.share.IdentityValueObjectMapper;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        uses = {IdentityValueObjectMapper.class},
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface AccountLinkMapper {
    default AccountLinkJpaEntity toAccountLinkJpaEntity(AccountLink accountLink) {
        return AccountLinkJpaEntity.builder()
                .id(accountLink.getId())
                .accountId(accountLink.getAccountId().value())
                .targetType(accountLink.getTargetType().toString())
                .targetId(accountLink.getTargetId())
                .build();
    }

    static AccountLink toAccountLinkDomain(AccountLinkJpaEntity entity) {
        return AccountLink.fromExistingBuilder()
                .id(entity.getId())
                .accountId(new AccountId(entity.getAccountId()))
                .targetId(entity.getTargetId())
                .targetType(AccountLinkType.valueOf(entity.getTargetType()))
                .build();
    }
}
