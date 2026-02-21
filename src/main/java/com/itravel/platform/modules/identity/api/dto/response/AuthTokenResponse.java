package com.itravel.platform.modules.identity.api.dto.response;

import java.time.Instant;

public record AuthTokenResponse(
        String accessToken,
        String refreshToken,
        Instant expiresAt
) {}
