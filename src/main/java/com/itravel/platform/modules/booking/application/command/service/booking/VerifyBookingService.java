package com.itravel.platform.modules.booking.application.command.service.booking;

import com.itravel.platform.modules.booking.application.exception.BookingNotFoundException;
import com.itravel.platform.modules.booking.application.port.in.booking.VerifyBookingUseCase;
import com.itravel.platform.modules.booking.application.port.out.booking.BookingRepository;
import com.itravel.platform.modules.booking.domain.booking.Booking;
import com.itravel.platform.modules.booking.domain.booking.BookingCode;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class VerifyBookingService implements VerifyBookingUseCase {
    BookingRepository bookingRepository;

    @Override
    @Transactional
    public void execute(String code) {
        Booking booking = bookingRepository.findByCode(new BookingCode(code))
                .orElseThrow(BookingNotFoundException::new);
        booking.verify();
    }
}
