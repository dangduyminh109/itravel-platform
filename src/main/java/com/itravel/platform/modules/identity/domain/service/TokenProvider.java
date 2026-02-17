package com.itravel.platform.modules.identity.domain.service;

import com.itravel.platform.modules.identity.domain.aggregate.enums.AccountLinkType;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.AccountId;
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
