package com.itravel.platform.modules.tour.application.dto;

public record ParticipantLimitDTO(
        Integer minParticipants,
        Integer maxParticipants
) {}
