package com.itravel.platform.modules.identity.application.port.out.account;

import java.util.Optional;

import com.itravel.platform.modules.identity.application.dto.AccountDTO;

public interface AccountQueryPort {
    Optional<AccountDTO> getAccount(String targetId);
    Optional<AccountDTO> getAccountByAccountId(String accountId);
}
