package com.itravel.platform.modules.tour.api.mapper;

import com.itravel.platform.modules.tour.api.dto.request.*;
import com.itravel.platform.modules.tour.api.dto.response.ScheduleDetailResponse;
import com.itravel.platform.modules.tour.application.command.model.schedule.CreateScheduleCommand;
import com.itravel.platform.modules.tour.application.command.model.schedule.DeleteScheduleCommand;
import com.itravel.platform.modules.tour.application.command.model.schedule.UpdateScheduleCommand;
import com.itravel.platform.modules.tour.application.dto.ScheduleDetailDTO;
import com.itravel.platform.modules.tour.share.*;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        uses = {
                TourValueObjectMapper.class,
                ScheduleValueObjectMapper.class
        },
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface ScheduleRestMapper {
    CreateScheduleCommand toCreateScheduleCommand(CreateScheduleRequest request);

    UpdateScheduleCommand toUpdateScheduleCommand(Long id,UpdateScheduleRequest request);

    DeleteScheduleCommand toDeleteScheduleCommand(Long id);

    ScheduleDetailResponse toScheduleDetailResponse(ScheduleDetailDTO dto);
}
