package com.itravel.platform.modules.tour.api.dto.response;

public record ParticipantLimitResponse(
        Integer minParticipants,
        Integer maxParticipants
) {
}
