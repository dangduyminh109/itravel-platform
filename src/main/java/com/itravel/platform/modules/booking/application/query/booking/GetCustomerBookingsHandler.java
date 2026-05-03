package com.itravel.platform.modules.booking.application.query.booking;

import com.itravel.platform.modules.booking.application.dto.BookingListItemDTO;
import com.itravel.platform.modules.booking.application.port.out.booking.BookingQueryPort;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class GetCustomerBookingsHandler {
    BookingQueryPort bookingQueryPort;

    public Page<BookingListItemDTO> handle(String customerId, Pageable pageable) {
        return bookingQueryPort.getBookingsByCustomerId(customerId, pageable);
    }
}
