package com.itravel.platform.modules.tour.share;

import com.itravel.platform.modules.tour.domain.aggregate.valueobject.AvailableSeats;
import com.itravel.platform.modules.tour.domain.aggregate.valueobject.DepartureDate;
import com.itravel.platform.modules.tour.domain.aggregate.valueobject.ScheduleId;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.time.LocalDate;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface ScheduleValueObjectMapper {
    default ScheduleId toScheduleId(Long id) {
        return id != null ? new ScheduleId(id) : null;
    }

    default Long fromScheduleId(ScheduleId id) {
        return id != null ? id.value() : null;
    }

    default DepartureDate toDepartureDate(LocalDate value) {
        return value != null ? new DepartureDate(value) : null;
    }

    default LocalDate fromDepartureDate(DepartureDate date) {
        return date != null ? date.value() : null;
    }

    default AvailableSeats toAvailableSeats(Integer value) {
        return value != null ? new AvailableSeats(value) : null;
    }

    default Integer fromAvailableSeats(AvailableSeats seats) {
        return seats != null ? seats.value() : null;
    }
}

