package com.itravel.platform.modules.identity.domain.repository;

import com.itravel.platform.modules.identity.domain.aggregate.Account;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.AccountId;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.Email;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.Username;

import java.util.Optional;

public interface AccountRepository {
    Optional<Account> findById(AccountId id);
    Optional<Account> findByUsername(Username username);
    Optional<Account> findByEmail(Email email);
    void save(Account account);
    void destroy(AccountId accountId);
}
