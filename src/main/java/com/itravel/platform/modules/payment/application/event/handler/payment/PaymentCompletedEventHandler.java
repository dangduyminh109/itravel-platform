package com.itravel.platform.modules.payment.application.event.handler.payment;

import com.itravel.platform.modules.booking.application.command.model.booking.PayBookingCommand;
import com.itravel.platform.modules.booking.application.port.in.booking.facade.BookingCommandFacade;
import com.itravel.platform.modules.booking.application.port.out.booking.BookingRepository;
import com.itravel.platform.modules.booking.domain.booking.BookingCode;
import com.itravel.platform.modules.payment.domain.event.PaymentCompletedEvent;
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
public class PaymentCompletedEventHandler {
    BookingRepository bookingRepository;
    BookingCommandFacade bookingCommandFacade;

    @Async
    @EventListener
    public void handle(PaymentCompletedEvent event) {
        log.info("Handling PaymentCompletedEvent for booking code: {}", event.referenceCode());
        
        bookingRepository.findByCode(new BookingCode(event.referenceCode()))
                .ifPresent(booking -> {
                    bookingCommandFacade.payBooking(new PayBookingCommand(
                            booking.getId(),
                            event.gatewayTransactionId()
                    ));
                });
    }
}
