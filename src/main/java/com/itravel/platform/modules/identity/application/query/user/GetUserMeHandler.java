package com.itravel.platform.modules.identity.application.query.user;

import com.itravel.platform.common.utils.SecurityUtils;
import com.itravel.platform.modules.identity.application.dto.AccountLinkDTO;
import com.itravel.platform.modules.identity.application.dto.UserDetailDTO;
import com.itravel.platform.modules.identity.application.exception.AccountLinkNotExistException;
import com.itravel.platform.modules.identity.application.exception.UserNotExistException;
import com.itravel.platform.modules.identity.application.port.out.accountlink.AccountLinkQueryPort;
import com.itravel.platform.modules.identity.application.port.out.user.UserQueryPort;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class GetUserMeHandler {
    UserQueryPort userQueryPort;
    AccountLinkQueryPort accountLinkQueryPort;

    public UserDetailDTO getMe(String accountId) {
        AccountLinkDTO link = accountLinkQueryPort.getByAccountId(accountId)
                .orElseThrow(AccountLinkNotExistException::new);
        return userQueryPort.getUser(link.targetId())
                .orElseThrow(UserNotExistException::new);
    }

    public UserDetailDTO getMe() {
        String accountId = SecurityUtils.getCurrentAccountId();
        return getMe(accountId);
    }
}
