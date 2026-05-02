package com.itravel.platform.modules.booking.application.dto;

import lombok.Builder;

@Builder
public record ContactInfoDTO(
        String fullName,
        String email,
        String phone,
        String address
) {}
