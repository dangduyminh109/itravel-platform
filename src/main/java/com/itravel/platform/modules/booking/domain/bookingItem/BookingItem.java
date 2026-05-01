package com.itravel.platform.modules.booking.domain.bookingItem;

import com.itravel.platform.modules.booking.domain.exception.InvalidBookingItemReferenceException;
import com.itravel.platform.modules.booking.domain.exception.InvalidServiceTypeException;
import com.itravel.platform.modules.booking.domain.exception.PriceBreakdownRequiredException;
import com.itravel.platform.modules.booking.domain.exception.SnapshotDataRequiredException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookingItem {
    BookingItemId id;
    ServiceType serviceType;
    String referenceId;
    ServiceSnapshot snapshotData;
    List<PriceLine> priceBreakdown;

    private BookingItem(
            BookingItemId id,
            ServiceType serviceType,
            String referenceId,
            ServiceSnapshot snapshotData,
            List<PriceLine> priceBreakdown) {
        this.id = id;
        this.serviceType = serviceType;
        this.referenceId = referenceId;
        this.snapshotData = snapshotData;
        this.priceBreakdown = Collections.unmodifiableList(new ArrayList<>(priceBreakdown));
    }

    public static BookingItem create(
            ServiceType serviceType,
            String referenceId,
            ServiceSnapshot snapshotData,
            List<PriceLine> priceBreakdown) {
        if (serviceType == null) {
            throw new InvalidServiceTypeException();
        }
        if (referenceId == null || referenceId.isBlank()) {
            throw new InvalidBookingItemReferenceException();
        }
        if (snapshotData == null) {
            throw new SnapshotDataRequiredException();
        }
        if (priceBreakdown == null || priceBreakdown.isEmpty()) {
            throw new PriceBreakdownRequiredException();
        }
        return new BookingItem(BookingItemId.generate(), serviceType, referenceId, snapshotData, priceBreakdown);
    }

    public static BookingItem fromExisting(
            BookingItemId id,
            ServiceType serviceType,
            String referenceId,
            ServiceSnapshot snapshotData,
            List<PriceLine> priceBreakdown) {
        return new BookingItem(id, serviceType, referenceId, snapshotData, priceBreakdown);
    }

    public BigDecimal getSubtotal() {
        return priceBreakdown.stream()
                .map(PriceLine::total)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
