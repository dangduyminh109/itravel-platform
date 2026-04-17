package com.itravel.platform.modules.identity.application.dto;

public record CustomerGeneralInfoDTO(
        Long totalCustomers,
        Long totalActiveCustomers,
        Long totalInactiveCustomers,
        Long newCustomers
) {}

