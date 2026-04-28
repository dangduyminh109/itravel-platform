package com.itravel.platform.modules.tour.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ItineraryRequest {
        Long id;
        @NotNull(message = "TOUR_ITINERARY_DAY_NUMBER_CANNOT_BE_NULL")
        Integer dayNumber;
        @NotBlank(message = "TOUR_ITINERARY_TITLE_CANNOT_BE_BLANK")
        @Size(max = 200, message = "TOUR_ITINERARY_TITLE_TOO_LONG")
        String title;
        String description;
        @NotNull(message = "TOUR_ITINERARY_ACTIVITIES_CANNOT_BE_NULL")
        @Size(min = 1, message = "TOUR_ITINERARY_ACTIVITIES_MIN")
        List<String> activities = new ArrayList<>();
}