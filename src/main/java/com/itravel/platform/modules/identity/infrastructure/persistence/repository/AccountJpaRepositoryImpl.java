package com.itravel.platform.modules.identity.infrastructure.persistence.repository;

import com.itravel.platform.modules.identity.domain.aggregate.Account;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.AccountId;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.Email;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.Username;
import com.itravel.platform.modules.identity.domain.repository.AccountRepository;
import com.itravel.platform.modules.identity.infrastructure.persistence.entity.AccountJpaEntity;
import com.itravel.platform.modules.identity.infrastructure.persistence.mapper.AccountMapper;
import java.util.Optional;

public class AccountJpaRepositoryImpl implements AccountRepository {
    AccountJpaRepository repository;
    AccountMapper mapper;

    @Override
    public Optional<Account> findById(AccountId id) {
        return repository.findById(id.value())
                .map(AccountMapper::toAccountDomain);
    }

    @Override
    public Optional<Account> findByUsername(Username username) {
        return repository.findByUsername(username.value())
                .map(AccountMapper::toAccountDomain);
    }

    @Override
    public Optional<Account> findByEmail(Email email) {
        return repository.findByEmail(email.value())
                .map(AccountMapper::toAccountDomain);
    }

    @Override
    public void save(Account account) {
        AccountJpaEntity accountJpa = mapper.toAccountJpaEntity(account);
        repository.save(accountJpa);
    }

    @Override
    public void destroy(AccountId accountId) {
        repository.findById(accountId.value())
                .ifPresent(repository::delete);
    }
}
