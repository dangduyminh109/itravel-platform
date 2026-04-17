package com.itravel.platform.modules.tour.infrastructure.persistence.mapper;

import com.itravel.platform.modules.tour.domain.schedule.Schedule;
import com.itravel.platform.modules.tour.domain.schedule.AvailableSeats;
import com.itravel.platform.modules.tour.domain.schedule.DepartureDate;
import com.itravel.platform.modules.tour.domain.schedule.ScheduleId;
import com.itravel.platform.modules.tour.infrastructure.persistence.entity.ScheduleJpaEntity;
import com.itravel.platform.modules.tour.share.ScheduleValueObjectMapper;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        uses = {ScheduleValueObjectMapper.class},
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface ScheduleMapper {
    ScheduleJpaEntity toScheduleJpaEntity(Schedule schedule);

    static Schedule toScheduleDomain(ScheduleJpaEntity entity) {
        return Schedule.fromExistingBuilder()
                .id(new ScheduleId(entity.getId()))
                .departureDate(new DepartureDate(entity.getDepartureDate()))
                .availableSeats(new AvailableSeats(entity.getAvailableSeats()))
                .surcharge(entity.getSurcharge())
                .status(entity.getStatus())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .deletedAt(entity.getDeletedAt())
                .build();
    }
}
