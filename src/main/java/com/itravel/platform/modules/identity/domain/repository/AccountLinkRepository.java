package com.itravel.platform.modules.identity.domain.repository;

import com.itravel.platform.modules.identity.domain.aggregate.AccountLink;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.AccountId;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface AccountLinkRepository {
    Optional<AccountLink> findByAccountId(AccountId id);
    Optional<AccountLink> findByTargetId(String targetId);
    void save(AccountLink accountLink);
    void destroy(Long id);
}
