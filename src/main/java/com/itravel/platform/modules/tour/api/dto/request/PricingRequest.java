package com.itravel.platform.modules.tour.api.dto.request;

import com.itravel.platform.modules.tour.domain.tour.CurrencyCode;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record PricingRequest(
        @NotNull(message = "TOUR_ORIGINAL_PRICE_CANNOT_BE_NULL")
        BigDecimal originalPrice,
        BigDecimal discountPrice,
        CurrencyCode currency
) {}
