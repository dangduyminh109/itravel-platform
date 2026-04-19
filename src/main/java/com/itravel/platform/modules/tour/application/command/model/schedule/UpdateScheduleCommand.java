package com.itravel.platform.modules.tour.application.command.model.schedule;

import com.itravel.platform.modules.tour.application.exception.ScheduleNotFoundException;
import com.itravel.platform.modules.tour.domain.schedule.DepartureDate;
import com.itravel.platform.modules.tour.domain.schedule.ScheduleId;
import com.itravel.platform.modules.tour.domain.schedule.ScheduleStatus;
import com.itravel.platform.modules.tour.domain.tour.Pricing;
import java.math.BigDecimal;

public record UpdateScheduleCommand(
        ScheduleId id,
        DepartureDate departureDate,
        Integer totalSeats,
        BigDecimal surcharge,
        Pricing pricing,
        ScheduleStatus status
) {
    public UpdateScheduleCommand {
        if(id == null) {
            throw new ScheduleNotFoundException();
        }
    }
}

