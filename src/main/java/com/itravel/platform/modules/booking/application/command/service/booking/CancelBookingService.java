package com.itravel.platform.modules.booking.application.command.service.booking;

import com.itravel.platform.modules.booking.application.command.model.booking.CancelBookingCommand;
import com.itravel.platform.modules.booking.application.exception.BookingNotFoundException;
import com.itravel.platform.modules.booking.application.port.in.booking.CancelBookingUseCase;
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
public class CancelBookingService implements CancelBookingUseCase {
    BookingRepository bookingRepository;
    BookingEventPublisher eventPublisher;

    @Override
    @Transactional
    public void execute(CancelBookingCommand command) {
        Booking booking = bookingRepository.findById(command.bookingId())
                .orElseThrow(BookingNotFoundException::new);
        
        booking.cancel();
        bookingRepository.save(booking);
        
        booking.getDomainEvents().forEach(eventPublisher::publish);
        booking.clearDomainEvents();
    }
}
