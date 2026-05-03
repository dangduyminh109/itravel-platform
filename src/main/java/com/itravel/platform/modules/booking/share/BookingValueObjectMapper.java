package com.itravel.platform.modules.booking.share;

import com.itravel.platform.modules.booking.domain.booking.BookingCode;
import com.itravel.platform.modules.booking.domain.booking.BookingId;
import com.itravel.platform.modules.booking.domain.bookingItem.BookingItemId;
import com.itravel.platform.modules.booking.domain.passenger.FullName;
import com.itravel.platform.modules.booking.domain.passenger.PassengerId;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookingValueObjectMapper {

    default String fromBookingId(BookingId bookingId) {
        return bookingId != null ? bookingId.value() : null;
    }

    default BookingId toBookingId(String value) {
        return value != null ? new BookingId(value) : null;
    }

    default String fromBookingCode(BookingCode bookingCode) {
        return bookingCode != null ? bookingCode.value() : null;
    }

    default BookingCode toBookingCode(String value) {
        return value != null ? new BookingCode(value) : null;
    }

    default String fromFullName(FullName fullName) {
        return fullName != null ? fullName.value() : null;
    }

    default FullName toFullName(String value) {
        return value != null ? new FullName(value) : null;
    }

    default String fromPassengerId(PassengerId passengerId) {
        return passengerId != null ? passengerId.value() : null;
    }

    default PassengerId toPassengerId(String value) {
        return value != null ? new PassengerId(value) : null;
    }

    default String fromBookingItemId(BookingItemId bookingItemId) {
        return bookingItemId != null ? bookingItemId.value() : null;
    }

    default BookingItemId toBookingItemId(String value) {
        return value != null ? new BookingItemId(value) : null;
    }
}
