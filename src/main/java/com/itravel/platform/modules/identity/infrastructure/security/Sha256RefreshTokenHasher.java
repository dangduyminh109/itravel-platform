package com.itravel.platform.modules.identity.infrastructure.security;

import com.itravel.platform.modules.identity.domain.aggregate.valueobject.TokenHash;
import com.itravel.platform.modules.identity.domain.service.RefreshTokenHasher;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

@Component
public class Sha256RefreshTokenHasher implements RefreshTokenHasher {

    @Value("${security.refresh-token.secret}")
    private String secret;

    @Override
    public TokenHash hash(String raw) {
        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), "HmacSHA256"));
            byte[] result = mac.doFinal(raw.getBytes(StandardCharsets.UTF_8));

            StringBuilder hex = new StringBuilder();
            for (byte b : result) {
                hex.append(String.format("%02x", b));
            }
            return new TokenHash(hex.toString());
        } catch (Exception e) {
            throw new IllegalStateException("Cannot hash refresh token", e);
        }
    }
}
