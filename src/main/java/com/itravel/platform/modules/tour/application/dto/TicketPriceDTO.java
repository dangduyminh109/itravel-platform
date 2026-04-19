package com.itravel.platform.modules.tour.application.dto;

import java.math.BigDecimal;

public record TicketPriceDTO(
        BigDecimal originalPrice,
        BigDecimal discountPrice
) {}
