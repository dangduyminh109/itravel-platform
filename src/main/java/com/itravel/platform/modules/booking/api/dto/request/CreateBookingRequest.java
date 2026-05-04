package com.itravel.platform.modules.booking.api.dto.request;

import com.itravel.platform.common.domain.enums.Gender;
import com.itravel.platform.modules.booking.domain.booking.ContactInfo;
import com.itravel.platform.modules.booking.domain.bookingItem.ServiceType;
import com.itravel.platform.modules.booking.domain.passenger.PassengerType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public record CreateBookingRequest(
        @NotBlank(message = "CUSTOMER_ID_CANNOT_BE_BLANK")
        String customerId,

        @NotNull(message = "CONTACT_INFO_CANNOT_BE_NULL")
        ContactInfo contactInfo,

        String note,

        @NotNull(message = "SERVICE_TYPE_CANNOT_BE_NULL")
        ServiceType serviceType,

        @NotBlank(message = "REFERENCE_ID_CANNOT_BE_BLANK")
        String referenceId,

        @NotEmpty(message = "PASSENGERS_CANNOT_BE_EMPTY")
        List<PassengerRequest> passengers
) {
    public record PassengerRequest(
            @NotNull(message = "FULL_NAME_CANNOT_BE_NULL")
            String fullName,

            @NotNull(message = "DATE_OF_BIRTH_CANNOT_BE_NULL")
            LocalDate dateOfBirth,

            @NotNull(message = "GENDER_CANNOT_BE_NULL")
            Gender gender,

            @NotBlank(message = "IDENTITY_NUMBER_CANNOT_BE_BLANK")
            String identityNumber,

            @NotNull(message = "PASSENGER_TYPE_CANNOT_BE_NULL")
            PassengerType selectedTicketType
    ) {}
}
