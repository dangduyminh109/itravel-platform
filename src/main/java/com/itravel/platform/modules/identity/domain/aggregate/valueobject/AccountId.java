package com.itravel.platform.modules.identity.domain.aggregate.valueobject;
import java.util.UUID;

public record AccountId(String value) {
    public static AccountId generate(){
        return new AccountId(UUID.randomUUID().toString());
    }
}
