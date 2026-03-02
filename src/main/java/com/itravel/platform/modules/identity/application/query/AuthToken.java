package com.itravel.platform.modules.identity.application.query;

import com.itravel.platform.modules.identity.domain.aggregate.valueobject.Email;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.Username;
import java.time.Instant;

public record AuthToken(
        Email email,
        Username userName,
        String accessToken,
        String refreshToken,
        Instant expiresAt
) {}
