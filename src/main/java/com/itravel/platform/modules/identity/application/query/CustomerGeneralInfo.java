package com.itravel.platform.modules.identity.application.query;

public record CustomerGeneralInfo(
        Long totalCustomers,
        Long totalActiveCustomers,
        Long totalInactiveCustomers,
        Long newCustomers
) {}
