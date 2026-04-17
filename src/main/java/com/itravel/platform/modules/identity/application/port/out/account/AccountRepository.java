package com.itravel.platform.modules.identity.application.port.out.account;

import com.itravel.platform.modules.identity.domain.account.Account;
import com.itravel.platform.modules.identity.domain.account.AccountId;
import com.itravel.platform.modules.identity.domain.user.Email;
import com.itravel.platform.modules.identity.domain.account.Username;
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

