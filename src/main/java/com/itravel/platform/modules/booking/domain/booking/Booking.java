package com.itravel.platform.modules.booking.domain.booking;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Booking {
    BookingStatus status;

}
