package com.itravel.platform.modules.tour.infrastructure.persistence.mapper;

import com.itravel.platform.modules.tour.domain.aggregate.Itinerary;
import com.itravel.platform.modules.tour.domain.aggregate.valueobject.ItineraryDayNumber;
import com.itravel.platform.modules.tour.domain.aggregate.valueobject.ItineraryId;
import com.itravel.platform.modules.tour.domain.aggregate.valueobject.ItineraryTitle;
import com.itravel.platform.modules.tour.infrastructure.persistence.entity.ItineraryJpaEntity;
import com.itravel.platform.modules.tour.share.ItineraryValueObjectMapper;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        uses = {ItineraryValueObjectMapper.class},
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface ItineraryMapper {
    ItineraryJpaEntity toItineraryJpaEntity(Itinerary itinerary);

    static Itinerary toItineraryDomain(ItineraryJpaEntity entity) {
        return Itinerary.fromExistingBuilder()
                .id(new ItineraryId(entity.getId()))
                .dayNumber(new ItineraryDayNumber(entity.getDayNumber()))
                .title(new ItineraryTitle(entity.getTitle()))
                .description(entity.getDescription())
                .activities(entity.getActivities())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .deletedAt(entity.getDeletedAt())
                .build();
    }
}

