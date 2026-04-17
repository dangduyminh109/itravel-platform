package com.itravel.platform.modules.identity.infrastructure.adapter;

import com.itravel.platform.modules.identity.domain.account.AccountLink;
import com.itravel.platform.modules.identity.domain.account.AccountId;
import com.itravel.platform.modules.identity.application.port.out.account.AccountLinkRepository;
import com.itravel.platform.modules.identity.infrastructure.persistence.entity.AccountLinkJpaEntity;
import com.itravel.platform.modules.identity.infrastructure.persistence.mapper.AccountLinkMapper;
import com.itravel.platform.modules.identity.infrastructure.persistence.repository.AccountLinkJpaRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountLinkRepositoryImpl implements AccountLinkRepository {
    AccountLinkJpaRepository repository;
    AccountLinkMapper accountLinkMapper;

    @Override
    public Optional<AccountLink> findByAccountId(AccountId id) {
        Optional<AccountLinkJpaEntity> accountLinkJpaEntity = repository.findByAccountId(id.value());
        return accountLinkJpaEntity.map(AccountLinkMapper::toAccountLinkDomain);
    }

    @Override
    public List<AccountLink> findAllByAccountId(AccountId id) {
        return repository.findAllByAccountId(id.value()).stream()
                .map(AccountLinkMapper::toAccountLinkDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<AccountLink> findByTargetId(String targetId) {
        Optional<AccountLinkJpaEntity> accountLinkJpaEntity = repository.findByTargetId(targetId);
        return accountLinkJpaEntity.map(AccountLinkMapper::toAccountLinkDomain);
    }

    @Override
    public void save(AccountLink accountLink) {
        AccountLinkJpaEntity entity = accountLinkMapper.toAccountLinkJpaEntity(accountLink);
        repository.save(entity);
    }

    @Override
    public void destroy(Long id) {
        repository.findById(id)
                .ifPresent(repository::delete);
    }
}
