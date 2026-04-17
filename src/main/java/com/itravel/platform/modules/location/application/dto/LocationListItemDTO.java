package com.itravel.platform.modules.location.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LocationListItemDTO {
    Long id;
    String name;
    String slug;
    String type;
    String status;
    LocationListItemDTO parent;
    Instant createdAt;
    Instant updatedAt;
    Instant deletedAt;
}
