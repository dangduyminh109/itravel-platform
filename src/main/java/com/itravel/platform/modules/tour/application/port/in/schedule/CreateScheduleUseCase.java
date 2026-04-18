package com.itravel.platform.modules.tour.application.port.in.schedule;

import com.itravel.platform.modules.tour.application.command.model.schedule.CreateScheduleCommand;
import com.itravel.platform.modules.tour.application.dto.ScheduleDetailDTO;

public interface CreateScheduleUseCase {
    ScheduleDetailDTO execute(CreateScheduleCommand command);
}