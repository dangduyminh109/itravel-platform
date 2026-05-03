package com.itravel.platform.modules.booking.application.event.handler.booking;

import com.itravel.platform.modules.booking.domain.event.BookingPaidEvent;
import com.itravel.platform.modules.booking.application.port.out.booking.BookingRepository;
import com.itravel.platform.modules.booking.application.port.out.booking.external.InvoicePort;
import com.itravel.platform.modules.booking.application.port.out.booking.external.NotificationPort;
import com.itravel.platform.modules.booking.domain.booking.BookingId;
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
public class BookingPaidEventHandler {
    BookingRepository bookingRepository;
    NotificationPort notificationPort;
    InvoicePort invoicePort;

    @Async
    @EventListener
    public void handle(BookingPaidEvent event) {
        log.info("Handling BookingPaidEvent for booking: {}", event.bookingCode());
        
        bookingRepository.findById(new BookingId(event.bookingId())).ifPresent(booking -> {
            String email = booking.getContactInfo().email();
            
            notificationPort.sendBookingConfirmation(event.bookingId(), email);
            
            invoicePort.generateInvoice(event.bookingId());
        });
    }
}
