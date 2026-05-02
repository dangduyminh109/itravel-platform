package com.itravel.platform.modules.booking.application.command.service.booking;

import com.itravel.platform.modules.booking.application.command.model.booking.PayBookingCommand;
import com.itravel.platform.modules.booking.application.exception.BookingNotFoundException;
import com.itravel.platform.modules.booking.application.port.in.booking.PayBookingUseCase;
import com.itravel.platform.modules.booking.application.port.out.booking.BookingRepository;
import com.itravel.platform.modules.booking.application.port.out.event.BookingEventPublisher;
import com.itravel.platform.modules.booking.domain.booking.Booking;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PayBookingService implements PayBookingUseCase {
    BookingRepository bookingRepository;
    BookingEventPublisher eventPublisher;

    @Override
    @Transactional
    public void execute(PayBookingCommand command) {
        Booking booking = bookingRepository.findById(command.bookingId())
                .orElseThrow(BookingNotFoundException::new);
        
        booking.markAsPaid();
        bookingRepository.save(booking);
        
        booking.getDomainEvents().forEach(eventPublisher::publish);
        booking.clearDomainEvents();
    }
}
