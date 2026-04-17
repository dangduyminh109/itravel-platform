package com.itravel.platform.modules.tour.domain.tour;

import com.itravel.platform.modules.tour.domain.tour.exception.InvalidMinParticipantsException;
import com.itravel.platform.modules.tour.domain.tour.exception.InvalidParticipantsRangeException;

public record ParticipantLimit(Integer minParticipants, Integer maxParticipants) {
    public ParticipantLimit {
        if (minParticipants != null && minParticipants < 0) {
            throw new InvalidMinParticipantsException();
        }
        if (maxParticipants != null && minParticipants != null && minParticipants > maxParticipants) {
            throw new InvalidParticipantsRangeException();
        }
    }
}
