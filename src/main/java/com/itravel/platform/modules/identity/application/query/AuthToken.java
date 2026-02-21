package com.itravel.platform.modules.identity.application.query;

import java.time.Instant;

public record AuthToken(
        String accessToken,
        String refreshToken,
        Instant expiresAt
) {}
