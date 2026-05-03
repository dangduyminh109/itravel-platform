package com.itravel.platform.modules.booking.application.event.handler.booking;

import com.itravel.platform.modules.booking.domain.event.BookingCancelledEvent;
import com.itravel.platform.modules.booking.application.port.out.booking.BookingRepository;
import com.itravel.platform.modules.booking.application.port.out.booking.external.TourCatalogPort;
import com.itravel.platform.modules.booking.domain.booking.BookingId;
import com.itravel.platform.modules.booking.domain.bookingItem.ServiceType;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookingCancelledEventHandler {
    BookingRepository bookingRepository;
    TourCatalogPort tourCatalogPort;

    @Async
    @EventListener
    public void handle(BookingCancelledEvent event) {
        bookingRepository.findById(new BookingId(event.bookingId())).ifPresent(booking -> {
            int quantityToUnlock = booking.getPassengers().size();
            
            booking.getBookingItems().stream()
                    .filter(item -> item.getServiceType() == ServiceType.TOUR)
                    .forEach(item -> tourCatalogPort.unlockInventory(item.getReferenceId(), quantityToUnlock));
        });
    }
}
