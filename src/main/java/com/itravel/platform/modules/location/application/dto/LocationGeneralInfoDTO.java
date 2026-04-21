package com.itravel.platform.modules.location.application.dto;

public record LocationGeneralInfoDTO(
        Long totalLocations,
        Long totalActiveLocations,
        Long totalInactiveLocations,
        Long newLocations
) {}
