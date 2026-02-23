package com.itravel.platform.modules.identity.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AddressRequest(
        @NotBlank(message = "ADDRESS_DETAIL_CANNOT_BE_BLANK")
        String detail,

        @NotNull(message = "WARD_ID_CANNOT_BE_NULL")
        Long wardId,

        @NotNull(message = "PROVINCE_ID_CANNOT_BE_NULL")
        Long provinceId
) {}

