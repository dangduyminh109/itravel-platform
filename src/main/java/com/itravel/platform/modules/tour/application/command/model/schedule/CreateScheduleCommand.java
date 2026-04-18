package com.itravel.platform.modules.tour.application.command.model.schedule;

import com.itravel.platform.modules.tour.domain.schedule.AvailableSeats;
import com.itravel.platform.modules.tour.domain.schedule.DepartureDate;
import com.itravel.platform.modules.tour.domain.schedule.ScheduleStatus;
import com.itravel.platform.modules.tour.domain.tour.TourId;

import java.math.BigDecimal;

public record CreateScheduleCommand(
        DepartureDate departureDate,
        AvailableSeats availableSeats,
        BigDecimal surcharge,
        ScheduleStatus status,
        TourId tourId
) {
}

