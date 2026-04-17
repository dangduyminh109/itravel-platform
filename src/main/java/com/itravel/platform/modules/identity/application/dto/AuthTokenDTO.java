package com.itravel.platform.modules.identity.application.dto;

import com.itravel.platform.modules.identity.domain.user.Email;
import com.itravel.platform.modules.identity.domain.account.Username;
import java.time.Instant;

public record AuthTokenDTO(
        Email email,
        Username userName,
        String accessToken,
        String refreshToken,
        Instant expiresAt
) {}

