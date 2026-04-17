package com.itravel.platform.modules.location.infrastructure.persistence.mapper;

import com.itravel.platform.common.domain.aggregate.valueobject.Slug;
import com.itravel.platform.modules.location.application.dto.LocationDetailDTO;
import com.itravel.platform.modules.location.application.dto.LocationListItemDTO;
import com.itravel.platform.modules.location.domain.location.LocationName;
import com.itravel.platform.modules.location.share.LocationValueObjectMapper;
import com.itravel.platform.modules.location.domain.location.Location;
import com.itravel.platform.modules.location.domain.location.LocationId;
import com.itravel.platform.modules.location.infrastructure.persistence.entity.LocationJpaEntity;
import com.itravel.platform.modules.tour.share.CommonValueObjectMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import java.util.ArrayList;
import java.util.Objects;

@Mapper(
        componentModel = "spring",
        uses = {LocationValueObjectMapper.class, CommonValueObjectMapper.class},
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface LocationMapper {
    LocationJpaEntity toLocationJpaEntity(Location location);

    static LocationListItemDTO toListItemDTO(LocationJpaEntity entity, boolean withParent, Integer level) {
        if (entity == null || level < 0) {
            return null;
        }
        Integer newLevel = level - 1;
        return LocationListItemDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .slug(entity.getSlug())
                .type(entity.getType().name())
                .status(entity.getStatus().name())
                .parent(entity.getParent() != null && withParent
                        ? toListItemDTO(entity.getParent(), true, newLevel)
                        : null)
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

    static LocationDetailDTO toDetailDTO(LocationJpaEntity entity, boolean withChildren, boolean withParent, Integer level) {
        if (entity == null || level < 0) {
            return null;
        }
        Integer newLevel = level - 1;
        return LocationDetailDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .slug(entity.getSlug())
                .type(entity.getType().name())
                .status(entity.getStatus().name())
                .parent(entity.getParent() != null && withParent
                        ? toDetailDTO(entity.getParent(), withChildren, true, newLevel)
                        : null
                )
                .children(withChildren && entity.getChildren() != null
                        ? entity.getChildren().stream()
                        .map(child -> toDetailDTO(child, true, withParent, newLevel))
                        .filter(Objects::nonNull)
                        .toList()
                        : new ArrayList<>())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .deletedAt(entity.getDeletedAt())
                .build();
    }
    
    static Location toLocationDomain(LocationJpaEntity entity, boolean withChildren, boolean withParent, Integer level) {
        if (entity == null || level < 0) {
            return null;
        }
        Integer newLevel = level - 1;
        return Location.fromExistingBuilder()
                .id(new LocationId(entity.getId()))
                .name(new LocationName(entity.getName()))
                .slug(new Slug(entity.getSlug()))
                .type(entity.getType())
                .status(entity.getStatus())
                .parent(entity.getParent() != null && withParent
                        ? toLocationDomain(entity.getParent(), withChildren, true, newLevel)
                        : null
                )
                .children(withChildren && entity.getChildren() != null
                        ? entity.getChildren().stream()
                        .map(child -> toLocationDomain(child, true, withParent, newLevel))
                        .filter(Objects::nonNull)
                        .toList()
                        : new ArrayList<>())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .deletedAt(entity.getDeletedAt())
                .build();
    }
}
