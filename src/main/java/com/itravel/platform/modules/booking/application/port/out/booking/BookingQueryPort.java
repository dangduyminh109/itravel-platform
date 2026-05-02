package com.itravel.platform.modules.booking.application.port.out.booking;

import com.itravel.platform.modules.booking.application.dto.BookingDetailDTO;
import com.itravel.platform.modules.booking.application.dto.BookingListItemDTO;
import java.util.List;
import java.util.Optional;

public interface BookingQueryPort {
    Optional<BookingDetailDTO> getBookingDetail(String bookingId);
    List<BookingListItemDTO> getBookingsByCustomerId(String customerId);
    List<BookingListItemDTO> getAllBookings();
}
