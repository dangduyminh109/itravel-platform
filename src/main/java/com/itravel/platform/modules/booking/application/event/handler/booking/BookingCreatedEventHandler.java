package com.itravel.platform.modules.booking.application.event.handler.booking;

import com.itravel.platform.modules.booking.domain.event.BookingCreatedEvent;
import com.itravel.platform.modules.booking.application.command.model.booking.ExpireBookingCommand;
import com.itravel.platform.modules.booking.application.port.in.booking.facade.BookingCommandFacade;
import com.itravel.platform.modules.booking.domain.booking.BookingId;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Slf4j
@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookingCreatedEventHandler {
    TaskScheduler taskScheduler;
    BookingCommandFacade bookingCommandFacade;

    @Async
    @EventListener
    public void handle(BookingCreatedEvent event) {
        log.info("Handling BookingCreatedEvent for booking: {}", event.bookingCode());
        
        Instant executeTime = Instant.now().plus(15, ChronoUnit.MINUTES);
        taskScheduler.schedule(() -> {
            try {
                bookingCommandFacade.expireBooking(new ExpireBookingCommand(new BookingId(event.bookingId())));
                log.info("Booking {} marked as EXPIRED automatically after 15 mins", event.bookingCode());
            } catch (Exception e) {
                log.error("Failed to auto expire booking {}", event.bookingCode(), e);
            }
        }, executeTime);
    }
}
