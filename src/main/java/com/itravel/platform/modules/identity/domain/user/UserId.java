package com.itravel.platform.modules.identity.domain.user;
import java.util.UUID;

public record UserId(String value) {
    public static UserId generate(){
        return new UserId(UUID.randomUUID().toString());
    }
}

