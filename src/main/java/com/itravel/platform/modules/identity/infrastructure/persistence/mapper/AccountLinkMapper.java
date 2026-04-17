package com.itravel.platform.modules.identity.infrastructure.persistence.mapper;

import com.itravel.platform.modules.identity.domain.account.AccountLink;
import com.itravel.platform.modules.identity.domain.account.AccountLinkType;
import com.itravel.platform.modules.identity.application.dto.AccountLinkDTO;
import com.itravel.platform.modules.identity.domain.account.AccountId;
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

    static AccountLinkDTO toAccountLinkDTO(AccountLinkJpaEntity entity) {
        return AccountLinkDTO.builder()
                .id(entity.getId())
                .accountId(entity.getAccountId())
                .targetType(entity.getTargetType())
                .targetId(entity.getTargetId())
                .build();
    }
}
