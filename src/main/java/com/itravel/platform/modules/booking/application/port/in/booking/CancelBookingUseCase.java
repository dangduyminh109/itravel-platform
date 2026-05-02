package com.itravel.platform.modules.booking.application.port.in.booking;

import com.itravel.platform.modules.booking.application.command.model.booking.CancelBookingCommand;

public interface CancelBookingUseCase {
    void execute(CancelBookingCommand command);
}
