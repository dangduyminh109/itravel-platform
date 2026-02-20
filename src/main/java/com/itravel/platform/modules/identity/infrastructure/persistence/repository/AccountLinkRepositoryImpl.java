package com.itravel.platform.modules.identity.infrastructure.persistence.repository;

import com.itravel.platform.modules.identity.domain.aggregate.AccountLink;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.AccountId;
import com.itravel.platform.modules.identity.domain.repository.AccountLinkRepository;
import com.itravel.platform.modules.identity.infrastructure.persistence.entity.AccountLinkJpaEntity;
import com.itravel.platform.modules.identity.infrastructure.persistence.mapper.AccountLinkMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class AccountLinkRepositoryImpl implements AccountLinkRepository {
    AccountLinkJpaRepository repository;

    @Override
    public Optional<AccountLink> findByAccountId(AccountId id) {
        Optional<AccountLinkJpaEntity> accountLinkJpaEntity = repository.findByAccountId(id.value());
        return accountLinkJpaEntity.map(AccountLinkMapper::toAccountLinkDomain).or(() -> Optional.ofNullable(null));
    }

    @Override
    public Optional<AccountLink> findByTargetId(String targetId) {
        Optional<AccountLinkJpaEntity> accountLinkJpaEntity = repository.findByTargetId(targetId);
        return accountLinkJpaEntity.map(AccountLinkMapper::toAccountLinkDomain).or(() -> Optional.ofNullable(null));
    }

    @Override
    public void save(AccountLink accountLink) {
        repository.save(
                AccountLinkJpaEntity.builder()
                        .accountId(accountLink.getAccountId().value())
                        .targetType(accountLink.getTargetType().toString())
                        .targetId(accountLink.getTargetId())
                        .build()
        );
    }

    @Override
    public void destroy(Long id) {
        repository.findById(id)
                .ifPresent(repository::delete);
    }
}
