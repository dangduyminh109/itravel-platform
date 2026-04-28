package com.itravel.platform.modules.tour.api.dto.request;

import com.itravel.platform.modules.tour.domain.tour.TourStatus;
import jakarta.validation.Valid;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateTourRequest {

        String name;
        String summary;
        String description;

        TourStatus status;

        @Valid
        PricingRequest pricing;

        @Valid
        TourDurationRequest duration;

        @Valid
        ParticipantLimitRequest participantLimit;

        @Valid
        ServicesRequest services;

        Long categoryId;
        Long departureLocationId;
        Long destinationLocationId;

        @Valid
        List<ItineraryRequest> itineraries = new ArrayList<>();

        @Valid
        List<ScheduleRequest> schedules = new ArrayList<>();

        @Valid
        List<TourImageRequest> tourImages = new ArrayList<>();

        @Valid
        List<String> removedImageUrls = new ArrayList<>();
}