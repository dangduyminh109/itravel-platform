package com.itravel.platform.modules.tour.domain.tour;

import com.itravel.platform.modules.tour.domain.tour.exception.InvalidDiscountPriceException;
import com.itravel.platform.modules.tour.domain.tour.exception.InvalidOriginPriceException;

import java.math.BigDecimal;
import java.util.Objects;

public record Pricing(BigDecimal originalPrice, BigDecimal discountPrice, CurrencyCode currency) {
    public Pricing {
        if (Objects.isNull(originalPrice) || originalPrice.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidOriginPriceException();
        }

        if (!Objects.isNull(discountPrice) && discountPrice.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidDiscountPriceException();
        }

        if (!Objects.isNull(discountPrice) && discountPrice.compareTo(originalPrice) >= 0) {
            throw new InvalidDiscountPriceException();
        }

        if(Objects.isNull(discountPrice)){
            currency = CurrencyCode.VND;
        }
    }
}
