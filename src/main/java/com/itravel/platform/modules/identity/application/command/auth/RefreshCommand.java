package com.itravel.platform.modules.identity.application.command.auth;

public record RefreshCommand(
        String refreshToken
) {}
