package com.itravel.platform.modules.booking.application.port.in.booking;

import com.itravel.platform.modules.booking.application.command.model.booking.PayBookingCommand;

public interface PayBookingUseCase {
    void execute(PayBookingCommand command);
}
