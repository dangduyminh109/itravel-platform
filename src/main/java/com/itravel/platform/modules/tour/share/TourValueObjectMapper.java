package com.itravel.platform.modules.tour.share;

import com.itravel.platform.modules.tour.domain.tour.CurrencyCode;
import com.itravel.platform.modules.tour.domain.tour.ParticipantLimit;
import com.itravel.platform.modules.tour.domain.tour.Pricing;
import com.itravel.platform.modules.tour.domain.tour.Services;
import com.itravel.platform.modules.tour.domain.tour.TourDuration;
import com.itravel.platform.modules.tour.domain.tour.TourId;
import com.itravel.platform.modules.tour.domain.tour.TourName;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import java.util.List;
import com.itravel.platform.modules.tour.api.dto.request.PricingRequest;
import com.itravel.platform.modules.tour.api.dto.request.TicketPriceRequest;
import com.itravel.platform.modules.tour.api.dto.response.PricingResponse;
import com.itravel.platform.modules.tour.api.dto.response.TicketPriceResponse;
import com.itravel.platform.modules.tour.application.dto.PricingDTO;
import com.itravel.platform.modules.tour.application.dto.TicketPriceDTO;
import com.itravel.platform.modules.tour.domain.tour.TicketPrice;
import com.itravel.platform.modules.tour.infrastructure.persistence.entity.TicketPriceJpaEntity;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface TourValueObjectMapper {
    default TourId toTourId(String id) {
        return id != null ? new TourId(id) : null;
    }

    default String fromTourId(TourId id) {
        return id != null ? id.value() : null;
    }

    default TourName toTourName(String name) {
        return name != null ? new TourName(name) : null;
    }

    default String fromTourName(TourName name) {
        return name != null ? name.value() : null;
    }

    default TicketPrice toTicketPrice(TicketPriceRequest request) {
        if (request == null) return null;
        return new TicketPrice(request.originalPrice(), request.discountPrice());
    }

    default Pricing toPricing(PricingRequest request) {
        if (request == null) return null;
        return new Pricing(
                toTicketPrice(request.adultPrice()),
                toTicketPrice(request.childPrice()),
                toTicketPrice(request.infantPrice()),
                request.singleSupplement(),
                request.currency()
        );
    }

    TicketPriceDTO toTicketPriceDTO(TicketPrice price);

    PricingDTO toPricingDTO(Pricing pricing);

    TicketPriceResponse toTicketPriceResponse(TicketPriceDTO dto);

    PricingResponse toPricingResponse(PricingDTO dto);

    TicketPriceJpaEntity toTicketPriceJpaEntity(TicketPrice pricing);

    default TicketPrice toTicketPrice(TicketPriceDTO dto) {
        if (dto == null) return null;
        return new TicketPrice(dto.originalPrice(), dto.discountPrice());
    }

    default Pricing toPricing(PricingDTO dto) {
        if (dto == null) return null;
        return new Pricing(
                toTicketPrice(dto.adultPrice()),
                toTicketPrice(dto.childPrice()),
                toTicketPrice(dto.infantPrice()),
                dto.singleSupplement(),
                dto.currency()
        );
    }

    default CurrencyCode toCurrencyCode(String currency) {
        return currency != null ? CurrencyCode.valueOf(currency) : null;
    }

    default String fromCurrencyCode(CurrencyCode currency) {
        return currency != null ? currency.name() : null;
    }

    default TourDuration toTourDuration(Integer days, Integer nights) {
        if (days == null && nights == null) {
            return null;
        }
        return new TourDuration(days, nights);
    }

    default Integer fromDurationDays(TourDuration duration) {
        return duration != null ? duration.days() : null;
    }

    default Integer fromDurationNights(TourDuration duration) {
        return duration != null ? duration.nights() : null;
    }

    default ParticipantLimit toParticipantLimit(Integer minParticipants, Integer maxParticipants) {
        if (minParticipants == null && maxParticipants == null) {
            return null;
        }
        return new ParticipantLimit(minParticipants, maxParticipants);
    }

    default Integer fromMinParticipants(ParticipantLimit limit) {
        return limit != null ? limit.minParticipants() : null;
    }

    default Integer fromMaxParticipants(ParticipantLimit limit) {
        return limit != null ? limit.maxParticipants() : null;
    }

    default Services toServices(List<String> includes, List<String> excludes) {
        if (includes == null && excludes == null) {
            return null;
        }
        return new Services(includes, excludes);
    }

    default List<String> fromIncludedServices(Services services) {
        return services != null ? services.includes() : null;
    }

    default List<String> fromExcludedServices(Services services) {
        return services != null ? services.excludes() : null;
    }
}
