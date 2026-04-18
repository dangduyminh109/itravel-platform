package com.itravel.platform.modules.tour.application.port.in.schedule;

import com.itravel.platform.modules.tour.application.command.model.schedule.DeleteScheduleCommand;

public interface DestroyScheduleUseCase {
    void execute(DeleteScheduleCommand command);
}
