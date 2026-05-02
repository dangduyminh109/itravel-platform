package com.itravel.platform.modules.tour.share;

import com.itravel.platform.modules.tour.domain.schedule.DepartureDate;
import com.itravel.platform.modules.tour.domain.schedule.ScheduleId;
import com.itravel.platform.modules.tour.domain.schedule.ScheduleSeats;
import com.itravel.platform.modules.tour.domain.schedule.ScheduleStatus;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.time.LocalDate;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface ScheduleValueObjectMapper {
    default ScheduleId toScheduleId(String id) {
        return id != null ? new ScheduleId(id) : null;
    }

    default String fromScheduleId(ScheduleId id) {
        return id != null ? id.value() : null;
    }

    default DepartureDate toDepartureDate(LocalDate value) {
        return value != null ? new DepartureDate(value) : null;
    }

    default LocalDate fromDepartureDate(DepartureDate date) {
        return date != null ? date.value() : null;
    }

    default ScheduleSeats toScheduleSeats(Integer total, Integer booked, Integer locked) {
        return new ScheduleSeats(total, booked, locked);
    }

    default ScheduleStatus toScheduleStatus(String status) {
        return status != null ? ScheduleStatus.valueOf(status) : null;
    }
}

