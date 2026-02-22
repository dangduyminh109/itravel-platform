package com.itravel.platform.modules.identity.domain.repository;

import com.itravel.platform.modules.identity.domain.aggregate.Account;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.AccountId;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.Email;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.Username;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface AccountRepository {
    Optional<Account> findById(AccountId id);
    Optional<Account> findByUsername(Username username);
    Optional<Account> findByEmail(Email email);
    Optional<Account> findByIdentifier(String identifier);
    Optional<Account> findByIdWithRolesAndPermissions(AccountId id);
    void save(Account account);
    void destroy(AccountId accountId);
}
