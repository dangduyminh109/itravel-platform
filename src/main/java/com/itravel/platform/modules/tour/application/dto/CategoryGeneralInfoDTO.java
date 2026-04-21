package com.itravel.platform.modules.tour.application.dto;

public record CategoryGeneralInfoDTO(
        Long totalCategories,
        Long totalActiveCategories,
        Long totalInactiveCategories,
        Long newCategories
) {}
