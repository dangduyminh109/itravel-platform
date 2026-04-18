package com.itravel.platform.modules.tour.application.command.model.tour;

import com.itravel.platform.modules.location.domain.location.LocationId;
import com.itravel.platform.modules.tour.domain.category.CategoryId;
import com.itravel.platform.modules.tour.domain.itinerary.ItineraryDayNumber;
import com.itravel.platform.modules.tour.domain.itinerary.ItineraryId;
import com.itravel.platform.modules.tour.domain.itinerary.ItineraryTitle;
import com.itravel.platform.modules.tour.domain.schedule.AvailableSeats;
import com.itravel.platform.modules.tour.domain.schedule.DepartureDate;
import com.itravel.platform.modules.tour.domain.schedule.ScheduleId;
import com.itravel.platform.modules.tour.domain.schedule.ScheduleStatus;
import com.itravel.platform.modules.tour.domain.tour.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;

public record UpdateTourCommand(
        TourId id,
        TourName name,
        String summary,
        String description,
        TourStatus status,
        Pricing pricing,
        TourDuration duration,
        ParticipantLimit participantLimit,
        Services services,
        CategoryId categoryId,
        LocationId departureLocationId,
        LocationId destinationLocationId,
        List<ItineraryCommand> itineraries,
        List<TourImageCommand> tourImages,
        List<ScheduleCommand> tourSchedules,
        List<String> removedImageUrls
) {
    public UpdateTourCommand {
        itineraries = itineraries == null ? List.of() : itineraries;
        tourImages = tourImages == null ? List.of() : tourImages;
        removedImageUrls = removedImageUrls == null ? List.of() : removedImageUrls;
    }

    public record ItineraryCommand(
            ItineraryId id,
            ItineraryDayNumber dayNumber,
            ItineraryTitle title,
            String description,
            List<String> activities
    ) {}

    public record ScheduleCommand(
            ScheduleId id,
            DepartureDate departureDate,
            AvailableSeats availableSeats,
            BigDecimal surcharge,
            ScheduleStatus status
    ) {}

    public record TourImageCommand(
            MultipartFile image,
            Boolean isThumbnail
    ) {}
}

