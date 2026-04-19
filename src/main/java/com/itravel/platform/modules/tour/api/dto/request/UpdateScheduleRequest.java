package com.itravel.platform.modules.tour.api.dto.request;

import com.itravel.platform.modules.identity.api.dto.request.validation.FutureDate;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.math.BigDecimal;
import java.time.LocalDate;

public record UpdateScheduleRequest(
        @NotNull(message = "DEPARTURE_DATE_CANNOT_BE_NULL")
        @FutureDate(message = "DEPARTURE_DATE_MUST_BE_FUTURE")
        LocalDate departureDate,

        @Min(value = 0, message = "TOTAL_SEATS_INVALID")
        Integer totalSeats,

        @DecimalMin(value = "0.0", inclusive = true, message = "SURCHARGE_MUST_BE_NON_NEGATIVE")
        BigDecimal surcharge,

        @Valid
        PricingRequest pricing,

        @NotNull(message = "STATUS_CANNOT_BE_NULL")
        @Pattern(regexp = "^$|^(OPEN|FULL|CANCELLED|COMPLETED)$", message = "SCHEDULE_STATUS_INVALID")
        String status
) {
}
