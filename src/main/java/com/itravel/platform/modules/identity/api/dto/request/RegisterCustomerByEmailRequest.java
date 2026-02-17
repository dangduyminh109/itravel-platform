package com.itravel.platform.modules.identity.api.dto.request;

public record RegisterCustomerByEmailRequest(
   String fullName,
   String email,
   String password
) {}
