package com.itravel.platform.modules.tour.domain.aggregate.valueobject;

import com.itravel.platform.modules.tour.domain.exception.InvalidScheduleIdException;

import java.util.Objects;

public record ScheduleId(Long value) {
    public ScheduleId {
        if (Objects.isNull(value)) {
            throw new InvalidScheduleIdException();
        }
    }
}

