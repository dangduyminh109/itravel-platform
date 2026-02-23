package com.itravel.platform.modules.identity.api.dto.response;

public record AddressResponse(
        String detail,
        Long wardId,
        Long provinceId
) {}

