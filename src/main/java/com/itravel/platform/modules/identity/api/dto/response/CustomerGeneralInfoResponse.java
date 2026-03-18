package com.itravel.platform.modules.identity.api.dto.response;

public record CustomerGeneralInfoResponse(
        Integer totalCustomers,
        Integer totalActiveCustomers,
        Integer totalInactiveCustomers,
        Integer newCustomers
) {}
