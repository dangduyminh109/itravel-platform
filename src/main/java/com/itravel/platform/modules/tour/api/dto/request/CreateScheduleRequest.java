package com.itravel.platform.modules.tour.api.dto.request;

import com.itravel.platform.modules.identity.api.dto.request.validation.FutureDate;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CreateScheduleRequest(
        @NotNull(message = "DEPARTURE_DATE_CANNOT_BE_NULL")
        @FutureDate(message = "DEPARTURE_DATE_MUST_BE_FUTURE")
        @DateTimeFormat(pattern = "dd/MM/yyyy")
        LocalDate departureDate,

        @NotNull(message = "AVAILABLE_SEATS_CANNOT_BE_NULL")
        @Min(value = 0, message = "AVAILABLE_SEATS_INVALID")
        Integer availableSeats,

        @NotNull(message = "SURCHARGE_CANNOT_BE_NULL")
        @DecimalMin(value = "0.0", inclusive = true, message = "SURCHARGE_MUST_BE_NON_NEGATIVE")
        BigDecimal surcharge,

        @NotNull(message = "STATUS_CANNOT_BE_NULL")
        @Pattern(regexp = "^$|^(OPEN|FULL|CANCELLED|COMPLETED)$", message = "SCHEDULE_STATUS_INVALID")
        String status,

        @NotBlank(message = "TOUR_ID_CANNOT_BE_BLANK")
        String tourId
) {
}
