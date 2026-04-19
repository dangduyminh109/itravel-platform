package com.itravel.platform.modules.tour.application.command.model.schedule;

import com.itravel.platform.modules.tour.domain.schedule.DepartureDate;
import com.itravel.platform.modules.tour.domain.schedule.ScheduleStatus;
import com.itravel.platform.modules.tour.domain.tour.Pricing;
import com.itravel.platform.modules.tour.domain.tour.TourId;

import java.math.BigDecimal;

public record CreateScheduleCommand(
        DepartureDate departureDate,
        Integer totalSeats,
        BigDecimal surcharge,
        Pricing pricing,
        ScheduleStatus status,
        TourId tourId
) {
}

