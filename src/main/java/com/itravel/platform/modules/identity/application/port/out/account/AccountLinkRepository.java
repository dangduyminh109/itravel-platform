package com.itravel.platform.modules.identity.application.port.out.account;

import com.itravel.platform.modules.identity.domain.account.AccountLink;
import com.itravel.platform.modules.identity.domain.account.AccountId;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface AccountLinkRepository {
    Optional<AccountLink> findByAccountId(AccountId id);
    List<AccountLink> findAllByAccountId(AccountId id);
    Optional<AccountLink> findByTargetId(String targetId);
    void save(AccountLink accountLink);
    void destroy(Long id);
}

