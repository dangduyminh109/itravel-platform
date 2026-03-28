package com.itravel.platform.modules.tour.share;

import com.itravel.platform.modules.tour.domain.aggregate.valueobject.ItineraryDayNumber;
import com.itravel.platform.modules.tour.domain.aggregate.valueobject.ItineraryId;
import com.itravel.platform.modules.tour.domain.aggregate.valueobject.ItineraryTitle;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface ItineraryValueObjectMapper {
    default ItineraryId toItineraryId(Long id) {
        return id != null ? new ItineraryId(id) : null;
    }

    default Long fromItineraryId(ItineraryId id) {
        return id != null ? id.value() : null;
    }

    default ItineraryDayNumber toItineraryDayNumber(Integer value) {
        return value != null ? new ItineraryDayNumber(value) : null;
    }

    default Integer fromItineraryDayNumber(ItineraryDayNumber dayNumber) {
        return dayNumber != null ? dayNumber.value() : null;
    }

    default ItineraryTitle toItineraryTitle(String value) {
        return value != null ? new ItineraryTitle(value) : null;
    }

    default String fromItineraryTitle(ItineraryTitle title) {
        return title != null ? title.value() : null;
    }
}

