package com.itravel.platform.modules.booking.application.dto;

import com.itravel.platform.common.domain.enums.Gender;
import com.itravel.platform.modules.booking.domain.passenger.PassengerType;
import java.time.LocalDate;

public record PassengerDTO(
        String fullName,
        LocalDate dateOfBirth,
        Gender gender,
        String identityNumber,
        PassengerType selectedTicketType
) {}
