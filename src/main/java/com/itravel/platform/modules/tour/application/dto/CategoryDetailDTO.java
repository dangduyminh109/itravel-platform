package com.itravel.platform.modules.tour.application.dto;

import lombok.Builder;
import java.time.Instant;

@Builder
public record CategoryDetailDTO(
    Long id,
    String name,
    String slug,
    String description,
    String status,
    Instant createdAt,
    Instant updatedAt,
    Instant deletedAt
){}
