package com.itravel.platform.modules.booking.application.port.in.booking.facade;

import com.itravel.platform.modules.booking.application.dto.BookingDetailDTO;
import com.itravel.platform.modules.booking.application.dto.BookingListItemDTO;
import com.itravel.platform.modules.booking.application.query.booking.GetBookingDetailHandler;
import com.itravel.platform.modules.booking.application.query.booking.GetBookingsHandler;
import com.itravel.platform.modules.booking.application.query.booking.GetCustomerBookingsHandler;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookingQueryFacade {
    GetBookingDetailHandler getBookingDetailHandler;
    GetCustomerBookingsHandler getCustomerBookingsHandler;
    GetBookingsHandler getBookingsHandler;

    public Optional<BookingDetailDTO> getBookingDetail(String bookingId) {
        return Optional.ofNullable(getBookingDetailHandler.handle(bookingId));
    }

    public List<BookingListItemDTO> getCustomerBookings(String customerId) {
        return getCustomerBookingsHandler.handle(customerId);
    }

    public List<BookingListItemDTO> getAllBookings() {
        return getBookingsHandler.handle();
    }
}
