package com.itravel.platform.modules.tour.api.mapper;

import com.itravel.platform.modules.location.share.LocationValueObjectMapper;
import com.itravel.platform.modules.tour.api.dto.request.*;
import com.itravel.platform.modules.tour.api.dto.response.*;
import com.itravel.platform.modules.tour.application.command.model.tour.*;
import com.itravel.platform.modules.tour.application.dto.*;
import com.itravel.platform.modules.tour.domain.tour.*;
import com.itravel.platform.modules.tour.share.CategoryValueObjectMapper;
import com.itravel.platform.modules.tour.share.ItineraryValueObjectMapper;
import com.itravel.platform.modules.tour.share.TourImageValueObjectMapper;
import com.itravel.platform.modules.tour.share.TourValueObjectMapper;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        uses = {
                TourValueObjectMapper.class,
                ItineraryValueObjectMapper.class,
                TourImageValueObjectMapper.class,
                CategoryValueObjectMapper.class,
                LocationValueObjectMapper.class
        },
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface TourRestMapper {
    CreateTourCommand toCreateTourCommand(CreateTourRequest request);

    UpdateTourCommand toUpdateTourCommand(String id, UpdateTourRequest request);

    UpdateTourStatusCommand toUpdateTourStatusCommand(String id, UpdateTourStatusRequest request);

    DeleteTourCommand toDeleteTourCommand(String id);

    RestoreTourCommand toRestoreTourCommand(String id);

    TourListItemResponse toTourListItemResponse(TourListItemDTO dto);

    TourDetailResponse toTourDetailResponse(TourDetailDTO dto);

    CreateTourCommand.ItineraryCommand toCreateItineraryCommand(ItineraryRequest request);

    UpdateTourCommand.ItineraryCommand toUpdateItineraryCommand(ItineraryRequest request);

    CreateTourCommand.TourImageCommand toCreateTourImageCommand(TourImageRequest request);

    UpdateTourCommand.TourImageCommand toUpdateTourImageCommand(TourImageRequest request);

    ItineraryResponse toItineraryResponse(ItineraryDTO dto);

    TourImageResponse toTourImageResponse(TourImageDTO dto);

    Pricing toPricing(PricingRequest request);

    TourDuration toTourDuration(TourDurationRequest request);

    ParticipantLimit toParticipantLimit(ParticipantLimitRequest request);

    Services toServices(ServicesRequest request);
}
