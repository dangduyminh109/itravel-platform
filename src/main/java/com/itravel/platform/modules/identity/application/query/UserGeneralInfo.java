package com.itravel.platform.modules.identity.application.query;

public record UserGeneralInfo(
        Long totalUsers,
        Long totalActiveUsers,
        Long totalInactiveUsers,
        Long newUsers
) {}
