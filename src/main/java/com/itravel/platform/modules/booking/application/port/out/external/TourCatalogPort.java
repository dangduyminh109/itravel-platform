package com.itravel.platform.modules.booking.application.port.out.external;

import com.itravel.platform.modules.booking.domain.bookingItem.PriceLine;
import com.itravel.platform.modules.booking.domain.bookingItem.TourSnapshot;
import com.itravel.platform.modules.booking.domain.passenger.PassengerType;
import java.util.List;

public interface TourCatalogPort {
    boolean verifyAndLockInventory(String scheduleId, int requiredQuantity);
    void unlockInventory(String scheduleId, int quantityToUnlock);
    TourSnapshot getTourSnapshot(String scheduleId);
    List<PriceLine> getPriceBreakdown(String scheduleId, List<PassengerType> passengerTypes);
}
