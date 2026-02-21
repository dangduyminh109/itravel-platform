package com.itravel.platform.modules.identity.api.dto.request;

public record LoginRequest(
   String identifier,
   String password
) {}
