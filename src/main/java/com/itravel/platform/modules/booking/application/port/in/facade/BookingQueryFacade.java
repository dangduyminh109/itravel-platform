package com.itravel.platform.modules.booking.application.port.in.facade;

import com.itravel.platform.modules.booking.application.dto.BookingDetailDTO;
import com.itravel.platform.modules.booking.application.dto.BookingListItemDTO;
import com.itravel.platform.modules.booking.domain.booking.BookingStatus;
import com.itravel.platform.modules.booking.application.query.booking.GetBookingDetailHandler;
import com.itravel.platform.modules.booking.application.query.booking.GetBookingsHandler;
import com.itravel.platform.modules.booking.application.query.booking.GetCustomerBookingsHandler;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookingQueryFacade {
    GetBookingDetailHandler getBookingDetailHandler;
    GetCustomerBookingsHandler getCustomerBookingsHandler;
    GetBookingsHandler getBookingsHandler;

    public BookingDetailDTO getBookingDetail(String bookingId) {
        return getBookingDetailHandler.handle(bookingId);
    }

    public Page<BookingListItemDTO> getCustomerBookings(String customerId, Pageable pageable) {
        return getCustomerBookingsHandler.handle(customerId, pageable);
    }

    public Page<BookingListItemDTO> getAllBookings(BookingStatus status, Pageable pageable) {
        return getBookingsHandler.handle(status, pageable);
    }
}
