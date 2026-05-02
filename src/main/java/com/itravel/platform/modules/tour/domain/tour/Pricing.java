package com.itravel.platform.modules.tour.domain.tour;

import java.math.BigDecimal;
import java.util.Objects;
import com.itravel.platform.common.domain.enums.CurrencyCode;
import com.itravel.platform.modules.tour.domain.tour.exception.MissingAdultPriceException;
import com.itravel.platform.modules.tour.domain.tour.exception.NegativeSingleSupplementException;

public record Pricing(
        TicketPrice adultPrice,
        TicketPrice childPrice,
        TicketPrice infantPrice,
        BigDecimal singleSupplement,
        CurrencyCode currency
) {
    public Pricing {
        if (Objects.isNull(adultPrice)) {
            throw new MissingAdultPriceException();
        }

        if (Objects.nonNull(singleSupplement) && singleSupplement.compareTo(BigDecimal.ZERO) < 0) {
            throw new NegativeSingleSupplementException();
        }

        if(Objects.isNull(currency)) {
            currency = CurrencyCode.VND;
        }
    }
}