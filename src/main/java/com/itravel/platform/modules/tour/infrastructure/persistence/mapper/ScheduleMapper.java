package com.itravel.platform.modules.tour.infrastructure.persistence.mapper;

import com.itravel.platform.modules.tour.application.dto.PricingDTO;
import com.itravel.platform.modules.tour.application.dto.ScheduleDetailDTO;
import com.itravel.platform.modules.tour.application.dto.TicketPriceDTO;
import com.itravel.platform.modules.tour.domain.schedule.*;
import com.itravel.platform.common.domain.enums.CurrencyCode;
import com.itravel.platform.modules.tour.domain.tour.Pricing;
import com.itravel.platform.modules.tour.domain.tour.TicketPrice;
import com.itravel.platform.modules.tour.domain.tour.TourId;
import com.itravel.platform.modules.tour.infrastructure.persistence.entity.ScheduleJpaEntity;
import com.itravel.platform.modules.tour.share.ScheduleValueObjectMapper;
import com.itravel.platform.modules.tour.share.TourValueObjectMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        uses = {TourValueObjectMapper.class, ScheduleValueObjectMapper.class},
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface ScheduleMapper {
        @Mapping(target = "totalSeats", source = "seats.total")
        @Mapping(target = "bookedSeats", source = "seats.booked")
        @Mapping(target = "lockedSeats", source = "seats.locked")
        @Mapping(target = "adultPrice.originalPrice", source = "pricing.adultPrice.originalPrice")
        @Mapping(target = "adultPrice.discountPrice", source = "pricing.adultPrice.discountPrice")
        @Mapping(target = "childPrice.originalPrice", source = "pricing.childPrice.originalPrice")
        @Mapping(target = "childPrice.discountPrice", source = "pricing.childPrice.discountPrice")
        @Mapping(target = "infantPrice.originalPrice", source = "pricing.infantPrice.originalPrice")
        @Mapping(target = "infantPrice.discountPrice", source = "pricing.infantPrice.discountPrice")
        @Mapping(target = "singleSupplement", source = "pricing.singleSupplement")
        @Mapping(target = "currency", source = "pricing.currency")
        ScheduleJpaEntity toScheduleJpaEntity(Schedule schedule);

        static Schedule toScheduleDomain(ScheduleJpaEntity entity) {
                if (entity == null)
                        return null;
                return Schedule.fromExistingBuilder()
                        .id(new ScheduleId(entity.getId()))
                        .departureDate(new DepartureDate(entity.getDepartureDate()))
                        .seats(new ScheduleSeats(entity.getTotalSeats(), entity.getBookedSeats(),
                                        entity.getLockedSeats()))
                        .pricing(new Pricing(
                                        entity.getAdultPrice() != null ? new TicketPrice(
                                                        entity.getAdultPrice().getOriginalPrice(),
                                                        entity.getAdultPrice().getDiscountPrice()) : null,
                                        entity.getChildPrice() != null ? new TicketPrice(
                                                        entity.getChildPrice().getOriginalPrice(),
                                                        entity.getChildPrice().getDiscountPrice()) : null,
                                        entity.getInfantPrice() != null ? new TicketPrice(
                                                        entity.getInfantPrice().getOriginalPrice(),
                                                        entity.getInfantPrice().getDiscountPrice()) : null,
                                        entity.getSingleSupplement(),
                                        entity.getCurrency() != null
                                                        ? CurrencyCode.valueOf(entity.getCurrency())
                                                        : CurrencyCode.VND))
                        .surcharge(entity.getSurcharge())
                        .status(entity.getStatus())
                        .tourId(new TourId(entity.getTourId()))
                        .version(entity.getVersion())
                        .createdAt(entity.getCreatedAt())
                        .updatedAt(entity.getUpdatedAt())
                        .deletedAt(entity.getDeletedAt())
                        .build();
        }

        static ScheduleDetailDTO toScheduleDetailDTO(ScheduleJpaEntity entity) {
                if (entity == null)
                        return null;
                ScheduleSeats seats = new ScheduleSeats(entity.getTotalSeats(), entity.getBookedSeats(),
                                entity.getLockedSeats());
                return ScheduleDetailDTO.builder()
                        .id(entity.getId())
                        .departureDate(entity.getDepartureDate())
                        .surcharge(entity.getSurcharge())
                        .status(entity.getStatus().name())
                        .availableSeats(seats.available())
                        .totalSeats(entity.getTotalSeats())
                        .bookedSeats(entity.getBookedSeats())
                        .lockedSeats(entity.getLockedSeats())
                        .pricing(new PricingDTO(
                                        entity.getAdultPrice() != null ? new TicketPriceDTO(
                                                        entity.getAdultPrice().getOriginalPrice(),
                                                        entity.getAdultPrice().getDiscountPrice()) : null,
                                        entity.getChildPrice() != null ? new TicketPriceDTO(
                                                        entity.getChildPrice().getOriginalPrice(),
                                                        entity.getChildPrice().getDiscountPrice()) : null,
                                        entity.getInfantPrice() != null ? new TicketPriceDTO(
                                                        entity.getInfantPrice().getOriginalPrice(),
                                                        entity.getInfantPrice().getDiscountPrice()) : null,
                                        entity.getSingleSupplement(),
                                        entity.getCurrency() != null
                                                        ? CurrencyCode.valueOf(entity.getCurrency())
                                                        : null))
                        .createdAt(entity.getCreatedAt())
                        .updatedAt(entity.getUpdatedAt())
                        .deletedAt(entity.getDeletedAt())
                        .build();
        }
}
