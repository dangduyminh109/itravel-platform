package com.itravel.platform.modules.tour.application.port.in.schedule;

import com.itravel.platform.modules.tour.application.command.model.schedule.UpdateScheduleCommand;
import com.itravel.platform.modules.tour.application.dto.ScheduleDetailDTO;

public interface UpdateScheduleUseCase {
    ScheduleDetailDTO execute(UpdateScheduleCommand command);
}
