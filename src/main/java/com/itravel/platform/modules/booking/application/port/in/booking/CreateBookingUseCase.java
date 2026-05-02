package com.itravel.platform.modules.booking.application.port.in.booking;

import com.itravel.platform.modules.booking.application.command.model.booking.CreateBookingCommand;
import com.itravel.platform.modules.booking.application.dto.BookingDetailDTO;

public interface CreateBookingUseCase {
    BookingDetailDTO execute(CreateBookingCommand command);
}
