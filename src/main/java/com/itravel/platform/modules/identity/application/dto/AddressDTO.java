package com.itravel.platform.modules.identity.application.dto;

import lombok.Builder;

@Builder
public record AddressDTO(
        String detail,
        Long wardId,
        Long provinceId
) {}
