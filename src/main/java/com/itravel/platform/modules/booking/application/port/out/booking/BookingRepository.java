package com.itravel.platform.modules.booking.application.port.out.booking;

import com.itravel.platform.modules.booking.application.dto.BookingDetailDTO;
import com.itravel.platform.modules.booking.domain.booking.Booking;
import com.itravel.platform.modules.booking.domain.booking.BookingId;
import com.itravel.platform.modules.booking.domain.booking.BookingCode;
import java.util.Optional;

public interface BookingRepository {
    BookingDetailDTO save(Booking booking);
    Optional<Booking> findById(BookingId id);
    Optional<Booking> findByCode(BookingCode code);
}
