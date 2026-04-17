package com.itravel.platform.modules.identity.application.port.out.accountlink;

import com.itravel.platform.modules.identity.application.dto.AccountLinkDTO;

import java.util.List;
import java.util.Optional;

public interface AccountLinkQueryPort {
    Optional<AccountLinkDTO> getByTargetId(String targetId);
    Optional<AccountLinkDTO> getByAccountId(String accountId);
    List<AccountLinkDTO> getAllByAccountId(String accountId);
}
