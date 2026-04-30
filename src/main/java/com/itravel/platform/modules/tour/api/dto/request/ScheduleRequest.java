package com.itravel.platform.modules.tour.api.dto.request;

import com.itravel.platform.modules.identity.api.dto.request.validation.FutureDate;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ScheduleRequest {
        Long id;

        @NotNull(message = "DEPARTURE_DATE_CANNOT_BE_NULL")
        @FutureDate(message = "DEPARTURE_DATE_MUST_BE_FUTURE")
        @DateTimeFormat(pattern = "HH:mm:ss dd/MM/yyyy")
        LocalDateTime departureDate;

        @Min(value = 0, message = "TOTAL_SEATS_INVALID")
        Integer totalSeats;

        @DecimalMin(value = "0.0", inclusive = true, message = "SURCHARGE_MUST_BE_NON_NEGATIVE")
        BigDecimal surcharge;

        @Valid
        PricingRequest pricing;

        @NotNull(message = "STATUS_CANNOT_BE_NULL")
        @Pattern(regexp = "^$|^(UPCOMING|OPEN|FULL|CANCELLED|COMPLETED)$",
                message = "SCHEDULE_STATUS_INVALID")
        String status;
}