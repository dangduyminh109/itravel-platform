package com.itravel.platform.modules.tour.api.dto.response;

import java.math.BigDecimal;

public record PricingResponse(
        TicketPriceResponse adultPrice,
        TicketPriceResponse childPrice,
        TicketPriceResponse infantPrice,
        BigDecimal singleSupplement,
        String currency
) {}
