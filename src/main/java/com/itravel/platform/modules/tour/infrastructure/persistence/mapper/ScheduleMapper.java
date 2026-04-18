package com.itravel.platform.modules.tour.infrastructure.persistence.mapper;

import com.itravel.platform.modules.tour.application.dto.ScheduleDetailDTO;
import com.itravel.platform.modules.tour.domain.schedule.Schedule;
import com.itravel.platform.modules.tour.domain.schedule.AvailableSeats;
import com.itravel.platform.modules.tour.domain.schedule.DepartureDate;
import com.itravel.platform.modules.tour.domain.schedule.ScheduleId;
import com.itravel.platform.modules.tour.domain.tour.TourId;
import com.itravel.platform.modules.tour.infrastructure.persistence.entity.ScheduleJpaEntity;
import com.itravel.platform.modules.tour.share.ScheduleValueObjectMapper;
import com.itravel.platform.modules.tour.share.TourValueObjectMapper;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        uses = {TourValueObjectMapper.class,ScheduleValueObjectMapper.class},
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
                .tourId(new TourId(entity.getTourId()))
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .deletedAt(entity.getDeletedAt())
                .build();
    }

    static ScheduleDetailDTO toScheduleDetailDTO(ScheduleJpaEntity entity) {
        return ScheduleDetailDTO.builder()
                .id(entity.getId())
                .departureDate(entity.getDepartureDate())
                .surcharge(entity.getSurcharge())
                .status(entity.getStatus().name())
                .availableSeats(entity.getAvailableSeats())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .deletedAt(entity.getDeletedAt())
                .build();
    }
}
