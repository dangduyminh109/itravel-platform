package com.itravel.platform.modules.booking.application.command.model.booking;

import com.itravel.platform.modules.booking.domain.booking.BookingId;

public record CancelBookingCommand(
        BookingId bookingId
) {}
