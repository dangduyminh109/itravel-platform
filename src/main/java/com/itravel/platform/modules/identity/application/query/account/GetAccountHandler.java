package com.itravel.platform.modules.identity.application.query.account;

import com.itravel.platform.modules.identity.application.dto.AccountDTO;
import com.itravel.platform.modules.identity.application.exception.AccountNotExistException;
import com.itravel.platform.modules.identity.application.port.out.account.AccountQueryPort;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class GetAccountHandler {
    AccountQueryPort queryPort;

    public AccountDTO getAccount(String targetId) {
        return queryPort.getAccount(targetId)
                .orElseThrow(AccountNotExistException::new);
    }

    public AccountDTO getAccountByAccountId(String accountId) {
        return queryPort.getAccountByAccountId(accountId)
                .orElseThrow(AccountNotExistException::new);
    }
}
