package com.itravel.platform.modules.identity.domain.service;

import com.itravel.platform.modules.identity.domain.account.AccountLinkType;
import com.itravel.platform.modules.identity.domain.account.AccountId;
import com.nimbusds.jose.JOSEException;
import org.springframework.stereotype.Component;

@Component
public interface TokenProvider {
    String generateAccessToken(AccountId accountId,
                               AccountLinkType accountType,
                               String RoleList,
                               String permissionList
    ) throws JOSEException;
}
