package com.itravel.platform.modules.booking.application.port.in.booking;

import com.itravel.platform.modules.booking.application.command.model.booking.ExpireBookingCommand;

public interface ExpireBookingUseCase {
    void execute(ExpireBookingCommand command);
}
