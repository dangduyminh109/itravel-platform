package com.itravel.platform.modules.booking.application.port.out.booking;

import com.itravel.platform.modules.booking.application.dto.BookingDetailDTO;
import com.itravel.platform.modules.booking.application.dto.BookingListItemDTO;
import com.itravel.platform.modules.booking.domain.booking.BookingStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Optional;

public interface BookingQueryPort {
    Optional<BookingDetailDTO> getBookingDetail(String bookingId);
    Optional<BookingDetailDTO> getBookingDetailByBookingCode(String code);
    Page<BookingListItemDTO> getBookingsByCustomerId(String customerId, Pageable pageable);
    Page<BookingListItemDTO> getAllBookings(BookingStatus status, Pageable pageable);
}
