package com.itravel.platform.modules.location.application.dto;

import lombok.*;

import java.time.Instant;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LocationDetailDTO {
    Long id;
    String name;
    String slug;
    String type;
    String status;
    LocationDetailDTO parent;
    List<LocationDetailDTO> children;
    Instant createdAt;
    Instant updatedAt;
    Instant deletedAt;
}
