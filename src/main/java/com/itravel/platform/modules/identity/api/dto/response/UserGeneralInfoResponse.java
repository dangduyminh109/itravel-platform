package com.itravel.platform.modules.identity.api.dto.response;

public record UserGeneralInfoResponse(
        Integer totalUsers,
        Integer totalActiveUsers,
        Integer totalInactiveUsers,
        Integer newUsers
) {}
