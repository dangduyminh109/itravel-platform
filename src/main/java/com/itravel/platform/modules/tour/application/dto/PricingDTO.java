package com.itravel.platform.modules.tour.application.dto;

import com.itravel.platform.common.domain.enums.CurrencyCode;
import java.math.BigDecimal;

public record PricingDTO(
                TicketPriceDTO adultPrice,
                TicketPriceDTO childPrice,
                TicketPriceDTO infantPrice,
                BigDecimal singleSupplement,
                CurrencyCode currency) {
}
