package com.itravel.platform.modules.identity.domain.customer;
import java.util.UUID;

public record CustomerId(String value) {
    public static CustomerId generate(){
        return new CustomerId(UUID.randomUUID().toString());
    }
}

