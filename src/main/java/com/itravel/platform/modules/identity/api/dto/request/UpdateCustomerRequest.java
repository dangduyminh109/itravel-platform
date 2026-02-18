package com.itravel.platform.modules.identity.api.dto.request;

public record UpdateCustomerRequest(
        String fullName,
        String newPassword
) { }
