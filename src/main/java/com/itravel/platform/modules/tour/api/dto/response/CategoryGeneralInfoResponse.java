package com.itravel.platform.modules.tour.api.dto.response;

public record CategoryGeneralInfoResponse(
        Long totalCategories,
        Long totalActiveCategories,
        Long totalInactiveCategories,
        Long newCategories
) {}
