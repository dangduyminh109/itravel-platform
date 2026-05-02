package com.itravel.platform.modules.booking.application.command.model.booking;

import com.itravel.platform.common.domain.enums.Gender;
import com.itravel.platform.modules.booking.domain.booking.ContactInfo;
import com.itravel.platform.modules.booking.domain.bookingItem.ServiceType;
import com.itravel.platform.modules.booking.domain.passenger.FullName;
import com.itravel.platform.modules.booking.domain.passenger.PassengerType;

import java.time.LocalDate;
import java.util.List;

public record CreateBookingCommand(
        String customerId,
        ContactInfo contactInfo,
        String note,
        ServiceType serviceType,
        String referenceId,
        List<PassengerCommand> passengers
) {
    public record PassengerCommand(
            FullName fullName,
            LocalDate dateOfBirth,
            Gender gender,
            String identityNumber,
            PassengerType selectedTicketType
    ) {}
}
