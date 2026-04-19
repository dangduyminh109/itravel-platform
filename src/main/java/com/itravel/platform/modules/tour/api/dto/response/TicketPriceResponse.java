package com.itravel.platform.modules.tour.api.dto.response;

import java.math.BigDecimal;

public record TicketPriceResponse(
        BigDecimal originalPrice,
        BigDecimal discountPrice
) {}
