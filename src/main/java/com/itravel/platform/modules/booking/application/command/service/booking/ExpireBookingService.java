package com.itravel.platform.modules.booking.application.command.service.booking;

import com.itravel.platform.modules.booking.application.command.model.booking.ExpireBookingCommand;
import com.itravel.platform.modules.booking.application.exception.BookingNotFoundException;
import com.itravel.platform.modules.booking.application.port.in.booking.ExpireBookingUseCase;
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
public class ExpireBookingService implements ExpireBookingUseCase {
    BookingRepository bookingRepository;
    BookingEventPublisher eventPublisher;

    @Override
    @Transactional
    public void execute(ExpireBookingCommand command) {
        Booking booking = bookingRepository.findById(command.bookingId())
                .orElseThrow(BookingNotFoundException::new);
        
        booking.expire();
        bookingRepository.save(booking);
        
        booking.getDomainEvents().forEach(eventPublisher::publish);
        booking.clearDomainEvents();
    }
}
