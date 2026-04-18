package com.itravel.platform.modules.tour.application.command.model.schedule;

import com.itravel.platform.modules.tour.domain.schedule.ScheduleId;
import com.itravel.platform.modules.tour.domain.schedule.ScheduleStatus;

public record UpdateScheduleStatusCommand(
        ScheduleId id,
        ScheduleStatus status
) {}
