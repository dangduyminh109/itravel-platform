package com.itravel.platform.modules.tour.domain.itinerary;

import com.itravel.platform.common.domain.SoftDeletableAggregate;
import com.itravel.platform.modules.tour.domain.itinerary.exception.InvalidItineraryActivitiesException;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Itinerary extends SoftDeletableAggregate<ItineraryId> {
    ItineraryId id;
    ItineraryDayNumber dayNumber;
    ItineraryTitle title;
    String description;
    List<String> activities;

    private Itinerary(
            ItineraryDayNumber dayNumber,
            ItineraryTitle title,
            String description,
            List<String> activities
    ) {
        super(null);
        this.dayNumber = dayNumber;
        this.title = title;
        this.description = description;
        this.activities = normalizeActivities(activities);
    }

    private Itinerary(
            ItineraryId id,
            ItineraryDayNumber dayNumber,
            ItineraryTitle title,
            String description,
            List<String> activities,
            Instant createdAt,
            Instant updatedAt,
            Instant deletedAt
    ) {
        super(id, createdAt, updatedAt, deletedAt);
        this.id = id;
        this.dayNumber = dayNumber;
        this.title = title;
        this.description = description;
        this.activities = normalizeActivities(activities);
    }

    public static Itinerary create(
            ItineraryDayNumber dayNumber,
            ItineraryTitle title,
            String description,
            List<String> activities
    ) {
        return new Itinerary(
                dayNumber,
                title,
                description,
                activities
        );
    }

    @Builder(builderMethodName = "fromExistingBuilder")
    public static Itinerary fromExisting(
            ItineraryId id,
            ItineraryDayNumber dayNumber,
            ItineraryTitle title,
            String description,
            List<String> activities,
            Instant createdAt,
            Instant updatedAt,
            Instant deletedAt
    ) {
        return new Itinerary(
                id,
                dayNumber,
                title,
                description,
                activities,
                createdAt,
                updatedAt,
                deletedAt
        );
    }

    public void updateDayNumber(ItineraryDayNumber dayNumber) {
        this.dayNumber = dayNumber;
        touch();
    }

    public void updateTitle(ItineraryTitle title) {
        this.title = title;
        touch();
    }

    public void updateDescription(String description) {
        this.description = description;
        touch();
    }

    public void updateActivities(List<String> activities) {
        this.activities = normalizeActivities(activities);
        touch();
    }

    private static List<String> normalizeActivities(List<String> activities) {
        if (Objects.isNull(activities) || activities.isEmpty()) {
            throw new InvalidItineraryActivitiesException();
        }
        List<String> copy = new ArrayList<>(activities.size());
        for (String activity : activities) {
            if (Objects.isNull(activity) || activity.isBlank()) {
                throw new InvalidItineraryActivitiesException();
            }
            copy.add(activity.trim());
        }
        return List.copyOf(copy);
    }
}
