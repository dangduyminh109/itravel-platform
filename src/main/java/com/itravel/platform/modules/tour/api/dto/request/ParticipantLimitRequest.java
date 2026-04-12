package com.itravel.platform.modules.tour.api.dto.request;

public record ParticipantLimitRequest(
        Integer minParticipants,
        Integer maxParticipants
) {}