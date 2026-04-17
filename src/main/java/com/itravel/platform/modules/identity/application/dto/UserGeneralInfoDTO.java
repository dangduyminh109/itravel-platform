package com.itravel.platform.modules.identity.application.dto;

public record UserGeneralInfoDTO(
        Long totalUsers,
        Long totalActiveUsers,
        Long totalInactiveUsers,
        Long newUsers
) {}
