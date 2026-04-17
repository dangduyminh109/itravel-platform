package com.itravel.platform.modules.identity.application.query.accountlink;

import com.itravel.platform.modules.identity.application.dto.AccountLinkDTO;
import com.itravel.platform.modules.identity.application.exception.AccountLinkNotExistException;
import com.itravel.platform.modules.identity.application.port.out.accountlink.AccountLinkQueryPort;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class GetAccountLinkHandler {
    AccountLinkQueryPort queryPort;

    public AccountLinkDTO getByTargetId(String targetId) {
        return queryPort.getByTargetId(targetId)
                .orElseThrow(AccountLinkNotExistException::new);
    }

    public AccountLinkDTO getByAccountId(String accountId) {
        return queryPort.getByAccountId(accountId)
                .orElseThrow(AccountLinkNotExistException::new);
    }

    public List<AccountLinkDTO> getAllByAccountId(String accountId) {
        return queryPort.getAllByAccountId(accountId);
    }
}
