package com.itravel.platform.modules.tour.api.dto.request;

import com.itravel.platform.modules.tour.domain.tour.TourStatus;
import jakarta.validation.Valid;

import java.util.List;

public record UpdateTourRequest(
        String name,
        String summary,
        String description,
        TourStatus status,

        @Valid
        PricingRequest pricing,

        @Valid
        TourDurationRequest duration,

        @Valid
        ParticipantLimitRequest participantLimit,

        @Valid
        ServicesRequest services,

        Long categoryId,
        Long departureLocationId,
        Long destinationLocationId,

        @Valid
        List<ItineraryRequest> itineraries,

        @Valid
        List<TourImageRequest> tourImages,

        @Valid
        List<String> removedImageUrls
) {
        public UpdateTourRequest {
                itineraries = itineraries == null ? List.of() : itineraries;
                tourImages = tourImages == null ? List.of() : tourImages;
                removedImageUrls = removedImageUrls == null ? List.of() : removedImageUrls;
        }
}

