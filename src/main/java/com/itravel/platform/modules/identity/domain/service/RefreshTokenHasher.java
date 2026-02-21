package com.itravel.platform.modules.identity.domain.service;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.TokenHash;
import org.springframework.stereotype.Component;

@Component
public interface RefreshTokenHasher {
    TokenHash hash(String rawToken);
}
