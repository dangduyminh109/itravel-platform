package com.itravel.platform.modules.identity.domain.account;
import java.util.UUID;

public record AccountId(String value) {
    public static AccountId generate(){
        return new AccountId(UUID.randomUUID().toString());
    }
}

