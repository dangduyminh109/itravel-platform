package com.itravel.platform.modules.location.infrastructure.persistence.mapper;

import com.itravel.platform.common.domain.aggregate.valueobject.Slug;
import com.itravel.platform.modules.location.domain.aggregate.valueobject.LocationName;
import com.itravel.platform.modules.location.share.LocationValueObjectMapper;
import com.itravel.platform.modules.location.domain.aggregate.Location;
import com.itravel.platform.modules.location.domain.aggregate.valueobject.LocationId;
import com.itravel.platform.modules.location.infrastructure.persistence.entity.LocationJpaEntity;
import com.itravel.platform.modules.tour.share.CommonValueObjectMapper;
import org.mapstruct.Mapper;
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
    static Location toLocationDomain(LocationJpaEntity entity, boolean withChildren, boolean withParent, Integer level) {
        Integer newLevel = level-1;
        if(level >= 0){
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
        return null;
    }
}
