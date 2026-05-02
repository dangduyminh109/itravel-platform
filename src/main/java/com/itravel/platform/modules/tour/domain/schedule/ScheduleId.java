package com.itravel.platform.modules.tour.domain.schedule;

import com.itravel.platform.modules.tour.domain.schedule.exception.InvalidScheduleIdException;
import java.util.UUID;

public record ScheduleId(String value) {
    public ScheduleId {
        if (value == null || value.isBlank()) {
            throw new InvalidScheduleIdException();
        }
    }

    public static ScheduleId generate() {
        return new ScheduleId(UUID.randomUUID().toString());
    }
}

