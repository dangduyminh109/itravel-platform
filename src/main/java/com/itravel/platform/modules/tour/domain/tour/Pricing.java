package com.itravel.platform.modules.tour.domain.tour;

import com.itravel.platform.modules.tour.domain.tour.exception.InvalidDiscountPriceException;
import com.itravel.platform.modules.tour.domain.tour.exception.InvalidOriginPriceException;

import java.math.BigDecimal;
import java.util.Objects;

public record Pricing(BigDecimal originPrice, BigDecimal discountPrice, CurrencyCode currency) {
    public Pricing {
        if (Objects.isNull(originPrice) || originPrice.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidOriginPriceException();
        }

        if (!Objects.isNull(discountPrice) && discountPrice.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidDiscountPriceException();
        }

        if (!Objects.isNull(discountPrice) && discountPrice.compareTo(originPrice) >= 0) {
            throw new InvalidDiscountPriceException();
        }

        if(Objects.isNull(discountPrice)){
            currency = CurrencyCode.VND;
        }
    }
}
