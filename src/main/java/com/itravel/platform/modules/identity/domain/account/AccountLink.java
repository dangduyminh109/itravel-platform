package com.itravel.platform.modules.identity.domain.account;

import com.itravel.platform.modules.identity.domain.account.AccountLinkType;
import com.itravel.platform.modules.identity.domain.account.AccountId;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AccountLink {
    final Long id;
    final AccountId accountId;
    final AccountLinkType targetType;
    String targetId;

    public static AccountLink linkToCustomer(AccountId accountId, String customerId) {
        return new AccountLink(null, accountId, AccountLinkType.CUSTOMER, customerId);
    }

    public static AccountLink linkToSystemUser(AccountId accountId, String userId) {
        return new AccountLink(null, accountId, AccountLinkType.SYSTEM_USER, userId);
    }

    public boolean isCustomer() {
        return this.targetType == AccountLinkType.CUSTOMER;
    }

    public boolean isSystemUser() {
        return this.targetType == AccountLinkType.SYSTEM_USER;
    }

    @Builder(builderMethodName = "fromExistingBuilder")
    public static AccountLink fromExisting(
            Long id,
            AccountId accountId,
            AccountLinkType targetType,
            String targetId
    ) {
        AccountLink acc = new AccountLink(
                id, accountId,targetType,targetId
        );
        return acc;
    }
}

