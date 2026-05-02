package com.itravel.platform.modules.booking.application.query.booking;

import com.itravel.platform.modules.booking.application.dto.BookingListItemDTO;
import com.itravel.platform.modules.booking.application.port.out.booking.BookingQueryPort;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class GetBookingsHandler {

    BookingQueryPort bookingQueryPort;

    public List<BookingListItemDTO> handle() {
        return bookingQueryPort.getAllBookings();
    }
}
