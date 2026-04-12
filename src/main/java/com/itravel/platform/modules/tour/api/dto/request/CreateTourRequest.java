package com.itravel.platform.modules.tour.api.dto.request;

import com.itravel.platform.modules.tour.domain.tour.TourStatus;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record CreateTourRequest(
        @NotBlank(message = "TOUR_NAME_CANNOT_BE_BLANK")
        String name,
        String summary,
        String description,

        @NotNull(message = "TOUR_STATUS_CANNOT_BE_BLANK")
        TourStatus status,

        @Valid
        PricingRequest pricing,
        @Valid
        TourDurationRequest duration,
        @Valid
        ParticipantLimitRequest participantLimit,
        @Valid
        ServicesRequest services,

        @NotNull(message = "TOUR_CATEGORY_ID_CANNOT_BE_NULL")
        Long categoryId,
        @NotNull(message = "TOUR_DEPARTURE_LOCATION_ID_CANNOT_BE_NULL")
        Long departureLocationId,
        @NotNull(message = "TOUR_DESTINATION_LOCATION_ID_CANNOT_BE_NULL")
        Long destinationLocationId,

        @Valid
        List<ItineraryRequest> itineraries,
        @Valid
        List<TourImageRequest> tourImages
) {
        public CreateTourRequest {
                itineraries = itineraries == null ? List.of() : itineraries;
                tourImages = tourImages == null ? List.of() : tourImages;
        }
}
