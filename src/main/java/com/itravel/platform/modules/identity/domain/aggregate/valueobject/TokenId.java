package com.itravel.platform.modules.identity.domain.aggregate.valueobject;

import java.util.UUID;

public record TokenId(String value) {
    public static TokenId generate(){
        return new TokenId(UUID.randomUUID().toString());
    }
}
