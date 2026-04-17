package com.itravel.platform.modules.identity.application.dto;

import lombok.Builder;

@Builder
public record AccountLinkDTO(
        Long id,
        String accountId,
        String targetType,
        String targetId
) {}
