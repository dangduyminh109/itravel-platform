package com.itravel.platform.modules.tour.domain.schedule;

import java.util.Objects;

public record ScheduleSeats(Integer total, Integer booked, Integer locked) {
    public ScheduleSeats {
        if (Objects.isNull(total) || total < 0) {
            total = 0;
        }
        if (Objects.isNull(booked) || booked < 0) {
            booked = 0;
        }
        if (Objects.isNull(locked) || locked < 0) {
            locked = 0;
        }
    }

    public Integer available() {
        return total - booked - locked;
    }

    public static ScheduleSeats create(Integer total) {
        return new ScheduleSeats(total, 0, 0);
    }
}
