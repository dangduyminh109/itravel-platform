package com.itravel.platform.modules.booking.application.dto;

import com.itravel.platform.modules.booking.domain.booking.BookingStatus;
import com.itravel.platform.common.domain.enums.CurrencyCode;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Builder
public record BookingDetailDTO(
                String id,
                String bookingCode,
                String customerId,
                ContactInfoDTO contactInfo,
                String note,
                BookingStatus status,
                BigDecimal totalAmount,
                CurrencyCode currency,
                Instant expiredAt,
                Instant createdAt,
                List<BookingItemDTO> items,
                List<PassengerDetailDTO> passengers) {
        @Builder
        public record BookingItemDTO(
                        String id,
                        String serviceType,
                        String referenceId,
                        List<PriceLineDTO> priceBreakdown) {
        }

        public record PriceLineDTO(
                        String type,
                        String name,
                        BigDecimal unitPrice,
                        CurrencyCode currency,
                        int quantity,
                        BigDecimal total) {
        }

        @Builder
        public record PassengerDetailDTO(
                        String id,
                        String fullName,
                        String dateOfBirth,
                        String gender,
                        String identityNumber,
                        String passengerType) {
        }
}
