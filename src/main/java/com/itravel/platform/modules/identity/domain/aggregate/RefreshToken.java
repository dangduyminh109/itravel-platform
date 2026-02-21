package com.itravel.platform.modules.identity.domain.aggregate;

import com.itravel.platform.modules.identity.domain.aggregate.valueobject.AccountId;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.TokenHash;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.TokenId;
import com.itravel.platform.modules.identity.domain.exception.TokenNotOwnedException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.time.Duration;
import java.time.Instant;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RefreshToken {
    final TokenId id;
    final AccountId accountId;
    final TokenHash tokenHash;

    final Instant expiresAt;
    Instant revokedAt;
    final Instant createdAt;

    private RefreshToken(
            AccountId accountId,
            TokenHash tokenHash,
            Duration refreshDuration
    ) {
        this.id = TokenId.generate();
        this.accountId = accountId;
        this.tokenHash = tokenHash;
        this.expiresAt = Instant.now().plus(refreshDuration);
        this.createdAt = Instant.now();
        this.revokedAt = null;
    }

    @Builder(builderMethodName = "fromExistingBuilder")
    public static RefreshToken fromExisting (
            TokenId id,
            AccountId accountId,
            TokenHash tokenHash,
            Instant expiresAt,
            Instant revokedAt,
            Instant createdAt
    ) {
        return new RefreshToken(id, accountId,tokenHash,expiresAt,revokedAt,createdAt);
    }


    public static RefreshToken create(AccountId accountId, TokenHash tokenHash, Duration refreshDuration){
        return new RefreshToken(accountId,tokenHash,refreshDuration);
    }

    public void revoke(){
        this.revokedAt = Instant.now();
    }

    public boolean isRevoked() {
        return revokedAt != null;
    }

    public boolean isExpired() {
        return Instant.now().isAfter(expiresAt);
    }

    public void verifyOwner(AccountId accountId) {
        if (!this.accountId.equals(accountId)) {
            throw new TokenNotOwnedException();
        }
    }
}
