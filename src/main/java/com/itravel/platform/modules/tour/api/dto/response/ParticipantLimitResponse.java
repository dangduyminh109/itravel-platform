package com.itravel.platform.modules.tour.api.dto.response;

public record ParticipantLimit(
        Integer minParticipants,
        Integer maxParticipants
) {
}
