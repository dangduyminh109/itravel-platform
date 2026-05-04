package com.itravel.platform.modules.booking.application.query.booking;

import com.itravel.platform.modules.booking.application.dto.BookingDetailDTO;
import com.itravel.platform.modules.booking.application.exception.BookingNotFoundException;
import com.itravel.platform.modules.booking.application.port.out.booking.BookingQueryPort;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class GetBookingDetailByBookingCodeHandler {
    BookingQueryPort bookingQueryPort;

    public BookingDetailDTO handle(String bookingId) {
        return bookingQueryPort.getBookingDetailByBookingCode(bookingId)
                .orElseThrow(BookingNotFoundException::new);
    }
}
