package com.itravel.platform.modules.tour.application.command.model.schedule;

import com.itravel.platform.modules.tour.domain.schedule.ScheduleId;

public record RestoreScheduleCommand(ScheduleId id) {
}