package com.itravel.platform.modules.booking.infrastructure.persistence.mapper;

import com.itravel.platform.common.domain.aggregate.valueobject.Money;
import com.itravel.platform.common.domain.enums.CurrencyCode;
import com.itravel.platform.modules.booking.application.dto.BookingDetailDTO;
import com.itravel.platform.modules.booking.application.dto.BookingListItemDTO;
import com.itravel.platform.modules.booking.application.dto.ContactInfoDTO;
import com.itravel.platform.modules.booking.domain.booking.Booking;
import com.itravel.platform.modules.booking.domain.booking.BookingCode;
import com.itravel.platform.modules.booking.domain.booking.BookingId;
import com.itravel.platform.modules.booking.domain.booking.ContactInfo;
import com.itravel.platform.modules.booking.infrastructure.persistence.entity.BookingItemJpaEntity;
import com.itravel.platform.modules.booking.infrastructure.persistence.entity.BookingJpaEntity;
import com.itravel.platform.modules.booking.infrastructure.persistence.entity.PassengerJpaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {
        BookingItemMapper.class,
        PassengerMapper.class
}, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface BookingMapper {

        static BookingJpaEntity toBookingJpaEntity(Booking domain) {
                if (domain == null)
                        return null;
                BookingJpaEntity entity = BookingJpaEntity.builder()
                        .id(domain.getId() != null ? domain.getId().value() : null)
                        .bookingCode(domain.getBookingCode() != null ? domain.getBookingCode().value() : null)
                        .status(domain.getStatus())
                        .totalAmount(domain.getTotalAmount() != null
                                ? domain.getTotalAmount().getAmount().doubleValue()
                                : 0.0)
                        .currency(domain.getTotalAmount() != null
                                && domain.getTotalAmount().getCurrency() != null
                                        ? domain.getTotalAmount().getCurrency().name()
                                        : CurrencyCode.VND.name())
                        .customerId(domain.getCustomerId())
                        .expiredAt(domain.getExpiredAt())
                        .createdAt(domain.getCreatedAt())
                        .updatedAt(domain.getUpdatedAt())
                        .contactFullName(domain.getContactInfo() != null ? domain.getContactInfo().fullName() : null)
                        .contactEmail(domain.getContactInfo() != null ? domain.getContactInfo().email() : null)
                        .contactPhone(domain.getContactInfo() != null ? domain.getContactInfo().phone() : null)
                        .contactAddress(domain.getContactInfo() != null ? domain.getContactInfo().address() : null)
                        .note(domain.getNote())
                        .build();

                if (domain.getBookingItems() != null) {
                        List<BookingItemJpaEntity> items = domain.getBookingItems().stream()
                                .map(BookingItemMapper::toBookingItemJpaEntity)
                                .collect(Collectors.toList());
                        entity.setBookingItems(items);
                }

                if (domain.getPassengers() != null) {
                        List<PassengerJpaEntity> passengers = domain.getPassengers().stream()
                                .map(PassengerMapper::toPassengerJpaEntity)
                                .collect(Collectors.toList());
                        entity.setPassengers(passengers);
                }
                return entity;
        }

        static Booking toBookingDomain(BookingJpaEntity entity) {
                if (entity == null)
                        return null;
                ContactInfo contactInfo = new ContactInfo(
                                entity.getContactFullName(),
                                entity.getContactEmail(),
                                entity.getContactPhone(),
                                entity.getContactAddress());

                return Booking.fromExistingBuilder()
                                .id(entity.getId() != null ? new BookingId(entity.getId()) : null)
                                .bookingCode(entity.getBookingCode() != null ? new BookingCode(entity.getBookingCode()) : null)
                                .customerId(entity.getCustomerId())
                                .contactInfo(contactInfo)
                                .note(entity.getNote())
                                .status(entity.getStatus())
                                .totalAmount(entity.getTotalAmount() != null
                                        ? Money.of(BigDecimal.valueOf(entity.getTotalAmount()), entity.getCurrency() != null
                                                ? CurrencyCode.valueOf(entity.getCurrency())
                                                : CurrencyCode.VND)
                                        : Money.zero(CurrencyCode.VND))
                                .expiredAt(entity.getExpiredAt())
                                .createdAt(entity.getCreatedAt())
                                .updatedAt(entity.getUpdatedAt())
                                .deletedAt(null)
                                .bookingItems(entity.getBookingItems() != null ? entity.getBookingItems().stream()
                                        .map(BookingItemMapper::toBookingItemDomain)
                                        .collect(Collectors.toList()) : null)
                                .passengers(entity.getPassengers() != null ? entity.getPassengers().stream()
                                        .map(PassengerMapper::toPassengerDomain)
                                        .collect(Collectors.toList()): null)
                                .build();
        }

        static BookingDetailDTO toBookingDetailDTO(BookingJpaEntity entity) {
                if (entity == null) return null;

                ContactInfoDTO contactInfo = ContactInfoDTO.builder()
                        .fullName(entity.getContactFullName())
                        .email(entity.getContactEmail())
                        .phone(entity.getContactPhone())
                        .address(entity.getContactAddress())
                        .build();

                List<BookingDetailDTO.BookingItemDTO> items = entity.getBookingItems() != null
                        ? entity.getBookingItems().stream()
                                .map(item -> BookingDetailDTO.BookingItemDTO.builder()
                                        .id(item.getId())
                                        .serviceType(item.getServiceType() != null ? item.getServiceType().name() : null)
                                        .referenceId(item.getReferenceId())
                                        .priceBreakdown(List.of())
                                        .build())
                                .toList()
                        : List.of();

                List<BookingDetailDTO.PassengerDetailDTO> passengers = entity.getPassengers() != null
                        ? entity.getPassengers().stream()
                                .map(p -> BookingDetailDTO.PassengerDetailDTO.builder()
                                        .id(p.getId())
                                        .fullName(p.getFullName())
                                        .dateOfBirth(p.getDateOfBirth() != null ? p.getDateOfBirth().toString() : null)
                                        .gender(p.getGender())
                                        .identityNumber(p.getIdentityNumber())
                                        .passengerType(p.getPassengerType() != null ? p.getPassengerType().name() : null)
                                        .build())
                                .toList()
                        : List.of();

                return BookingDetailDTO.builder()
                        .id(entity.getId())
                        .bookingCode(entity.getBookingCode())
                        .customerId(entity.getCustomerId())
                        .contactInfo(contactInfo)
                        .note(entity.getNote())
                        .status(entity.getStatus())
                        .totalAmount(entity.getTotalAmount() != null ? BigDecimal.valueOf(entity.getTotalAmount()) : BigDecimal.ZERO)
                        .currency(entity.getCurrency() != null ? CurrencyCode.valueOf(entity.getCurrency()) : CurrencyCode.VND)
                        .expiredAt(entity.getExpiredAt())
                        .createdAt(entity.getCreatedAt())
                        .items(items)
                        .passengers(passengers)
                        .build();
        }

        static BookingListItemDTO toBookingListItemDTO(BookingJpaEntity entity) {
                if (entity == null) return null;

                ContactInfoDTO contactInfo = ContactInfoDTO.builder()
                        .fullName(entity.getContactFullName())
                        .email(entity.getContactEmail())
                        .phone(entity.getContactPhone())
                        .address(entity.getContactAddress())
                        .build();

                return BookingListItemDTO.builder()
                        .id(entity.getId())
                        .bookingCode(entity.getBookingCode())
                        .contactInfo(contactInfo)
                        .status(entity.getStatus())
                        .totalAmount(entity.getTotalAmount() != null ? BigDecimal.valueOf(entity.getTotalAmount()) : BigDecimal.ZERO)
                        .createdAt(entity.getCreatedAt())
                        .build();
        }
}
