package com.itravel.platform.modules.booking.application.dto;

import com.itravel.platform.modules.booking.domain.bookingItem.ServiceType;
import java.util.List;

public record CreateBookingRequestDTO(
        ContactInfoDTO contactInfo,
        String note,
        ServiceType serviceType,
        String referenceId,
        List<PassengerDTO> passengers
) {}
