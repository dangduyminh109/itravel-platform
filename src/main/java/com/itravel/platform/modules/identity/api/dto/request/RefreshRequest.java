package com.itravel.platform.modules.identity.api.dto.request;

public record RefreshRequest(
        String accountId,
        String refreshToken
) {}
