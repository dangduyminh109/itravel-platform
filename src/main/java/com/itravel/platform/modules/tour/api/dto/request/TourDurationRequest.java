package com.itravel.platform.modules.tour.api.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TourDurationRequest {
        @NotNull(message = "TOUR_DURATION_DAYS_CANNOT_BE_NULL")
        Integer days;
        @NotNull(message = "TOUR_DURATION_NIGHTS_CANNOT_BE_NULL")
        Integer nights;
}