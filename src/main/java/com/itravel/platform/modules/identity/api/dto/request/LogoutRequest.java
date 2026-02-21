package com.itravel.platform.modules.identity.api.dto.request;

public record LogoutRequest(
        String accountId,
        String refreshToken
) {}