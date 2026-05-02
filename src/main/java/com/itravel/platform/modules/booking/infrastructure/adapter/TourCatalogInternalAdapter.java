package com.itravel.platform.modules.booking.infrastructure.adapter;

import com.itravel.platform.modules.booking.application.port.out.external.TourCatalogPort;
import com.itravel.platform.modules.booking.domain.bookingItem.PriceLine;
import com.itravel.platform.modules.booking.domain.bookingItem.PriceLineType;
import com.itravel.platform.modules.booking.domain.bookingItem.TourSnapshot;
import com.itravel.platform.modules.booking.domain.passenger.PassengerType;
import com.itravel.platform.modules.tour.application.port.in.schedule.facade.ScheduleCommandFacade;
import com.itravel.platform.modules.tour.application.port.in.schedule.facade.ScheduleQueryFacade;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import com.itravel.platform.common.domain.aggregate.valueobject.Money;
import com.itravel.platform.common.domain.enums.CurrencyCode;
import com.itravel.platform.modules.tour.application.dto.ScheduleDetailDTO;
import com.itravel.platform.modules.tour.application.dto.PricingDTO;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TourCatalogInternalAdapter implements TourCatalogPort {

    ScheduleQueryFacade scheduleQueryFacade;
    ScheduleCommandFacade scheduleCommandFacade;

    @Override
    public boolean verifyAndLockInventory(String scheduleId, int requiredQuantity) {
        return true;
    }

    @Override
    public void unlockInventory(String scheduleId, int quantityToUnlock) {
    }

    @Override
    public TourSnapshot getTourSnapshot(String scheduleId) {
        return new TourSnapshot(
                "Tour Name Placeholder",
                null,
                "Departure",
                "Destination");
    }

    @Override
    public List<PriceLine> getPriceBreakdown(String scheduleId, List<PassengerType> passengerTypes) {
        ScheduleDetailDTO scheduleDetail = scheduleQueryFacade.getDetail(scheduleId);
        PricingDTO pricing = scheduleDetail.pricing();

        if (pricing == null)
            return List.of();

        return passengerTypes.stream()
                .collect(Collectors.groupingBy(type -> type, Collectors.counting()))
                .entrySet().stream()
                .map(entry -> {
                    PassengerType type = entry.getKey();
                    int quantity = entry.getValue().intValue();
                    BigDecimal unitPriceAmount = BigDecimal.ZERO;
                    PriceLineType priceLineType = PriceLineType.BASE_PRICE;
                    String priceLineName = "Người lớn";

                    if (type == PassengerType.ADULT && pricing.adultPrice() != null) {
                        unitPriceAmount = pricing.adultPrice().discountPrice() != null
                                ? pricing.adultPrice().discountPrice()
                                : pricing.adultPrice().originalPrice();
                        priceLineType = PriceLineType.BASE_PRICE;
                        priceLineName = "Người lớn";
                    } else if (type == PassengerType.CHILD && pricing.childPrice() != null) {
                        unitPriceAmount = pricing.childPrice().discountPrice() != null
                                ? pricing.childPrice().discountPrice()
                                : pricing.childPrice().originalPrice();
                        priceLineType = PriceLineType.CHILD_PRICE;
                        priceLineName = "Trẻ em";
                    } else if (type == PassengerType.INFANT && pricing.infantPrice() != null) {
                        unitPriceAmount = pricing.infantPrice().discountPrice() != null
                                ? pricing.infantPrice().discountPrice()
                                : pricing.infantPrice().originalPrice();
                        priceLineType = PriceLineType.INFANT_PRICE;
                        priceLineName = "Em bé";
                    }

                    CurrencyCode currency = pricing.currency() != null ? pricing.currency() : CurrencyCode.VND;
                    Money unitPrice = Money.of(unitPriceAmount, currency);

                    return PriceLine.of(priceLineType, priceLineName, unitPrice, quantity);
                }).collect(Collectors.toList());
    }
}
