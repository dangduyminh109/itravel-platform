package com.itravel.platform.modules.location.api.dto.response;

public record LocationGeneralInfoResponse(
        Long totalLocations,
        Long totalActiveLocations,
        Long totalInactiveLocations,
        Long newLocations
) {}
