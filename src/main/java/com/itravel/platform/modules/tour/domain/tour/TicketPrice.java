package com.itravel.platform.modules.tour.domain.tour;

import com.itravel.platform.modules.tour.domain.tour.exception.InvalidDiscountPriceException;
import com.itravel.platform.modules.tour.domain.tour.exception.InvalidOriginPriceException;

import java.math.BigDecimal;
import java.util.Objects;

public record TicketPrice(BigDecimal originalPrice, BigDecimal discountPrice) {
    public TicketPrice {
        if (Objects.isNull(originalPrice) || originalPrice.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidOriginPriceException();
        }

        if (Objects.nonNull(discountPrice)) {
            if (discountPrice.compareTo(BigDecimal.ZERO) <= 0) {
                throw new InvalidDiscountPriceException();
            }
            if (discountPrice.compareTo(originalPrice) >= 0) {
                throw new InvalidDiscountPriceException();
            }
        }
    }

    public BigDecimal getSellingPrice() {
        return Objects.nonNull(discountPrice) ? discountPrice : originalPrice;
    }
}