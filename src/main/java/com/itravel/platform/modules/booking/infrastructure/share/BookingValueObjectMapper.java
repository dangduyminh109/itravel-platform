package com.itravel.platform.modules.booking.infrastructure.share;

import com.itravel.platform.modules.booking.domain.booking.BookingCode;
import com.itravel.platform.modules.booking.domain.booking.BookingId;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookingValueObjectMapper {

    default String map(BookingId bookingId) {
        return bookingId != null ? bookingId.value() : null;
    }

    default BookingId mapToBookingId(String value) {
        return value != null ? new BookingId(value) : null;
    }

    default String map(BookingCode bookingCode) {
        return bookingCode != null ? bookingCode.value() : null;
    }

    default BookingCode mapToBookingCode(String value) {
        return value != null ? new BookingCode(value) : null;
    }
}
