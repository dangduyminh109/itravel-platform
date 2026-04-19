package com.itravel.platform.modules.tour.api.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.Valid;
import java.math.BigDecimal;
import com.itravel.platform.modules.tour.domain.tour.CurrencyCode;

public record PricingRequest(
        @Valid
        TicketPriceRequest adultPrice,

        @Valid
        TicketPriceRequest childPrice,

        @Valid
        TicketPriceRequest infantPrice,

        @DecimalMin(value = "0.0", inclusive = true, message = "SINGLE_SUPPLEMENT_MUST_BE_NON_NEGATIVE")
        BigDecimal singleSupplement,

        CurrencyCode currency
) {}
