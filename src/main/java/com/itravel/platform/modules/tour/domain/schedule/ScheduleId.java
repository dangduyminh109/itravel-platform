package com.itravel.platform.modules.tour.domain.schedule;

import com.itravel.platform.modules.tour.domain.schedule.exception.InvalidScheduleIdException;

import java.util.Objects;

public record ScheduleId(Long value) {
    public ScheduleId {
        if (Objects.isNull(value)) {
            throw new InvalidScheduleIdException();
        }
    }
}

