package com.itravel.platform.modules.tour.api.dto.request;

import jakarta.validation.constraints.DecimalMin;
import java.math.BigDecimal;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TicketPriceRequest {
        @DecimalMin(value = "0.0", inclusive = false, message = "ORIGINAL_PRICE_MUST_BE_POSITIVE")
        BigDecimal originalPrice;
        @DecimalMin(value = "0.0", inclusive = false, message = "DISCOUNT_PRICE_MUST_BE_POSITIVE")
        BigDecimal discountPrice;
}