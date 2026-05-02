package com.itravel.platform.modules.booking.application.port.in.facade;

import com.itravel.platform.modules.booking.application.command.model.booking.CancelBookingCommand;
import com.itravel.platform.modules.booking.application.command.model.booking.CreateBookingCommand;
import com.itravel.platform.modules.booking.application.command.model.booking.ExpireBookingCommand;
import com.itravel.platform.modules.booking.application.command.model.booking.PayBookingCommand;
import com.itravel.platform.modules.booking.application.dto.BookingDetailDTO;
import com.itravel.platform.modules.booking.application.port.in.booking.CancelBookingUseCase;
import com.itravel.platform.modules.booking.application.port.in.booking.CreateBookingUseCase;
import com.itravel.platform.modules.booking.application.port.in.booking.ExpireBookingUseCase;
import com.itravel.platform.modules.booking.application.port.in.booking.PayBookingUseCase;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookingCommandFacade {
    CreateBookingUseCase createBookingUseCase;
    CancelBookingUseCase cancelBookingUseCase;
    PayBookingUseCase payBookingUseCase;
    ExpireBookingUseCase expireBookingUseCase;

    public BookingDetailDTO createBooking(CreateBookingCommand command) {
        return createBookingUseCase.execute(command);
    }
    
    public void cancelBooking(CancelBookingCommand command) {
        cancelBookingUseCase.execute(command);
    }
    
    public void payBooking(PayBookingCommand command) {
        payBookingUseCase.execute(command);
    }
    
    public void expireBooking(ExpireBookingCommand command) {
        expireBookingUseCase.execute(command);
    }
}
