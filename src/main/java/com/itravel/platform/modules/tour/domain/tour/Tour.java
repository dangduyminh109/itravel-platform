package com.itravel.platform.modules.tour.domain.tour;

import com.itravel.platform.common.domain.SoftDeletableAggregate;
import com.itravel.platform.common.domain.aggregate.valueobject.Slug;
import com.itravel.platform.modules.location.domain.aggregate.valueobject.LocationId;
import com.itravel.platform.modules.tour.domain.category.Category;
import com.itravel.platform.modules.tour.domain.category.CategoryId;
import com.itravel.platform.modules.tour.domain.itinerary.Itinerary;
import com.itravel.platform.modules.tour.domain.schedule.Schedule;
import com.itravel.platform.modules.tour.domain.tourImage.TourImage;
import lombok.AccessLevel;
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
    CategoryId category;
    LocationId departureLocationId;
    LocationId destinationLocationId;

    TourDuration duration;
    ParticipantLimit participantLimit;

    List<Itinerary> itinerary;
    List<Schedule> schedule;
    List<TourImage> tourImage;


    private Tour(
    TourName name,
    String summary,
    String description,
    TourStatus status,
    Slug slug,
    Category category,
    Pricing pricing,
    LocationId departureLocationId,
    LocationId destinationLocationId,

    TourDuration duration,
    ParticipantLimit participantLimit,
    List<Itinerary> itinerary,
    List<Schedule> schedule,
    List<TourImage> tourImage
    ) {
        super(TourId.generate());

    }

    private Tour(
            TourId id,
            TourName name,
            String summary,
            String description,
            TourStatus status,
            Slug slug,
            Category category,
            Pricing pricing,
            LocationId departureLocationId,
            LocationId destinationLocationId,

            TourDuration duration,
            ParticipantLimit participantLimit,
            List<Itinerary> itinerary,
            List<Schedule> schedule,
            List<TourImage> tourImage,
            Instant createdAt,
            Instant updatedAt,
            Instant deletedAt
    ) {
        super(id, createdAt, updatedAt,deletedAt);
    }

//    public static Tour create(
//            TourId id,
//            TourName name,
//            String summary,
//            String description,
//            TourStatus status,
//            Slug slug,
//            Category category,
//            Pricing pricing,
//            LocationId departureLocationId,
//            LocationId destinationLocationId,
//
//            TourDuration duration,
//            ParticipantLimit participantLimit,
//            List<Itinerary> itinerary,
//            List<Schedule> schedule,
//            List<TourImage> tourImage
//    ) {
//        return new Tour(
//                null,
//                name,
//                summary,
//                description,
//                status,
//                Slug.toSlug(name.value()),
//                category,
//                pricing,
//                departureLocationId,
//                destinationLocationId,
//                duration,
//                participantLimit,
//                itinerary,
//                schedule,
//                tourImage
//        );
//    }
//
//    @Builder(builderMethodName = "fromExistingBuilder")
//    public static Tour fromExisting(
//            TourImageId id,
//            ImageUrl imageUrl,
//            Boolean isThumbnail,
//            Instant createdAt,
//            Instant updatedAt
//    ) {
//        return new Tour(
//                id,
//                imageUrl,
//                isThumbnail,
//                createdAt,
//                updatedAt
//        );
//    }
//
//    public void updateImageUrl(ImageUrl imageUrl) {
//        this.imageUrl = imageUrl;
//        touch();
//    }
//
//    public void updateIsThumbnail(Boolean isThumbnail) {
//        this.isThumbnail = isThumbnail;
//        touch();
//    }
}
