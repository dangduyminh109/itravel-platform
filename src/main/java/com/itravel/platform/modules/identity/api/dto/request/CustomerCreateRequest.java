package com.itravel.platform.modules.identity.api.dto.request;

public record CustomerCreateRequest(
   String fullName,
   String email,
   String password
) {}
