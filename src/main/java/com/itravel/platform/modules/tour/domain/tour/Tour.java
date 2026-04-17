package com.itravel.platform.modules.tour.domain.tour;

import com.itravel.platform.common.domain.SoftDeletableAggregate;
import com.itravel.platform.common.domain.aggregate.valueobject.Slug;
import com.itravel.platform.modules.location.domain.location.LocationId;
import com.itravel.platform.modules.tour.domain.category.CategoryId;
import com.itravel.platform.modules.tour.domain.itinerary.Itinerary;
import com.itravel.platform.modules.tour.domain.itinerary.exception.InvalidItineraryDayNumberException;
import com.itravel.platform.modules.tour.domain.tourImage.TourImage;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.time.Instant;
import java.util.List;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Tour extends SoftDeletableAggregate<TourId> {
    TourName name;
    String summary;
    String description;
    TourStatus status;
    Slug slug;

    Pricing pricing;
    TourDuration duration;
    ParticipantLimit participantLimit;
    Services services;

    CategoryId categoryId;
    LocationId departureLocationId;
    LocationId destinationLocationId;

    List<Itinerary> itineraries;
    List<TourImage> tourImages;

    private Tour(TourName name,
        String summary,
        String description,
        TourStatus status,
        Slug slug,

        Pricing pricing,
        TourDuration duration,
        ParticipantLimit participantLimit,
        Services services,

        CategoryId categoryId,
        LocationId departureLocationId,
        LocationId destinationLocationId,

        List<Itinerary> itineraries,
        List<TourImage> tourImages
    ) {
        super(TourId.generate());
        this.name = name;
        this.summary = summary;
        this.description = description;
        this.status = status;
        this.slug = slug;
        this.pricing = pricing;
        this.duration = duration;
        this.participantLimit = participantLimit;
        this.services = services;
        this.categoryId = categoryId;
        this.departureLocationId = departureLocationId;
        this.destinationLocationId = destinationLocationId;
        this.itineraries = itineraries;
        this.tourImages = tourImages;
    }

    private Tour(
            TourId id,
            TourName name,
            String summary,
            String description,
            TourStatus status,
            Slug slug,

            Pricing pricing,
            TourDuration duration,
            ParticipantLimit participantLimit,
            Services services,

            CategoryId categoryId,
            LocationId departureLocationId,
            LocationId destinationLocationId,

            List<Itinerary> itineraries,
            List<TourImage> tourImages,
            Instant createdAt,
            Instant updatedAt,
            Instant deletedAt
    ) {
        super(id, createdAt, updatedAt,deletedAt);
        this.name = name;
        this.summary = summary;
        this.description = description;
        this.status = status;
        this.slug = slug;
        this.pricing = pricing;
        this.duration = duration;
        this.participantLimit = participantLimit;
        this.services = services;
        this.categoryId = categoryId;
        this.departureLocationId = departureLocationId;
        this.destinationLocationId = destinationLocationId;
        this.itineraries = itineraries;
        this.tourImages = tourImages;
    }

    public static Tour create(
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

            List<Itinerary> itineraries,
            List<TourImage> tourImages
    ) {
        return new Tour(
                name,
                summary,
                description,
                status,
                Slug.toSlug(name.value()),

                pricing,
                duration,
                participantLimit,
                services,

                categoryId,
                departureLocationId,
                destinationLocationId,

                itineraries,
                tourImages
        );
    }

    @Builder(builderMethodName = "fromExistingBuilder")
    public static Tour fromExisting(
            TourId id,
            TourName name,
            String summary,
            String description,
            TourStatus status,
            Slug slug,

            Pricing pricing,
            TourDuration duration,
            ParticipantLimit participantLimit,
            Services services,

            CategoryId categoryId,
            LocationId departureLocationId,
            LocationId destinationLocationId,

            List<Itinerary> itineraries,
            List<TourImage> tourImages,
            Instant createdAt,
            Instant updatedAt,
            Instant deletedAt
    ) {
        return new Tour(
                id,
                name,
                summary,
                description,
                status,
                slug,

                pricing,
                duration,
                participantLimit,
                services,

                categoryId,
                departureLocationId,
                destinationLocationId,

                itineraries,
                tourImages,
                createdAt,
                updatedAt,
                deletedAt
        );
    }

    public void updateName(TourName name) {
        this.name = name;
        this.slug = Slug.toSlug(name.value());
        touch();
    }

    public void updateStatus(TourStatus status) {
        this.status = status;
        touch();
    }

    public void updateSummary(String summary) {
        this.summary = summary;
        touch();
    }

    public void updateDescription(String description) {
        this.description = description;
        touch();
    }

    public void updateCategory(CategoryId categoryId) {
        this.categoryId = categoryId;
        touch();
    }

    public void updateDepartureLocation(LocationId departureLocationId) {
        this.departureLocationId = departureLocationId;
        touch();
    }

    public void updateDestinationLocation(LocationId destinationLocationId) {
        this.destinationLocationId = destinationLocationId;
        touch();
    }

     public void updatePricing(Pricing pricing) {
        this.pricing = pricing;
        touch();
    }

    public void updateDuration(TourDuration duration) {
        this.duration = duration;
        touch();
    }

    public void updateParticipantLimit(ParticipantLimit participantLimit) {
        this.participantLimit = participantLimit;
        touch();
    }

    public void updateServices(Services services) {
        this.services = services;
        touch();
    }

    public void addItinerary(Itinerary itinerary) {
        if (this.itineraries.stream().anyMatch(item -> item.getDayNumber().equals(itinerary.getDayNumber()))) {
            throw new InvalidItineraryDayNumberException();
        }
        if(itinerary.getDayNumber().value() > this.duration.days()){
            throw new InvalidItineraryDayNumberException();
        }
        this.itineraries.add(itinerary);
        touch();
    }

    public void removeItinerary(Itinerary itinerary) {
        this.itineraries.removeIf((item) -> item.getId().equals(itinerary.getId()));
        touch();
    }

    public void removeItineraryById(Long id) {
        this.itineraries.removeIf((item) -> item.getId().value().equals(id));
        touch();
    }


    public void addTourImage(TourImage tourImage) {
        this.tourImages.add(tourImage);
        touch();
    }

    public void removeTourImage(TourImage tourImage) {
        this.tourImages.removeIf((item) -> item.getId().equals(tourImage.getId()));
        touch();
    }

    public void removeTourImageByUrl(String url) {
        this.tourImages.removeIf((item) -> item.getImageUrl().value().equals(url));
        touch();
    }

    public void updateItineraries(List<Itinerary> itineraries) {
        List<Integer> dayNumbers = itineraries.stream().map(item -> item.getDayNumber().value()).toList();
        if (dayNumbers.size() != dayNumbers.stream().distinct().count()) {
            throw new InvalidItineraryDayNumberException();
        }

        int maxDay = dayNumbers.stream()
                .max(Integer::compareTo)
                .orElse(0);

        if (maxDay > duration.days()) {
            throw new InvalidItineraryDayNumberException();
        }
        this.itineraries = itineraries;
        touch();
    }

    public void updateTourImages(List<TourImage> tourImages) {
        this.tourImages = tourImages;
        touch();
    }
}
