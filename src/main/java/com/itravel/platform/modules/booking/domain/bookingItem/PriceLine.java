package com.itravel.platform.modules.booking.domain.bookingItem;

import com.itravel.platform.modules.booking.domain.exception.InvalidPriceLineNameException;
import com.itravel.platform.modules.booking.domain.exception.InvalidPriceLineQuantityException;
import com.itravel.platform.modules.booking.domain.exception.InvalidUnitPriceException;
import com.itravel.platform.common.domain.aggregate.valueobject.Money;
import java.math.BigDecimal;

public record PriceLine(
        PriceLineType type,
        String name,
        Money unitPrice,
        int quantity,
        Money total
) {
    public PriceLine {
        if (name == null || name.isBlank()) {
            throw new InvalidPriceLineNameException();
        }
        if (unitPrice == null || unitPrice.getAmount().compareTo(BigDecimal.ZERO) < 0) {
            throw new InvalidUnitPriceException();
        }
        if (quantity <= 0) {
            throw new InvalidPriceLineQuantityException();
        }
        total = unitPrice.multiply(quantity);
    }

    public static PriceLine of(PriceLineType type, String name, Money unitPrice, int quantity) {
        return new PriceLine(type, name, unitPrice, quantity, unitPrice.multiply(quantity));
    }
}
