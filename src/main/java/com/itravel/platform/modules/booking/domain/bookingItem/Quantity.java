package com.itravel.platform.modules.booking.domain.bookingItem;

import com.itravel.platform.modules.booking.domain.exception.InvalidQuantityException;

public record Quantity(int value) {
    public Quantity {
        if (value <= 0) {
            throw new InvalidQuantityException();
        }
    }
}