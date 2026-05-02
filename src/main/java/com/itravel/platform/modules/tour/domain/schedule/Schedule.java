package com.itravel.platform.modules.tour.domain.schedule;

import com.itravel.platform.common.domain.SoftDeletableAggregate;
import com.itravel.platform.modules.tour.domain.schedule.exception.InvalidScheduleStatusException;
import com.itravel.platform.modules.tour.domain.schedule.exception.TotalSeatsLowerThanMinParticipantsException;
import com.itravel.platform.modules.tour.domain.tour.Pricing;
import com.itravel.platform.modules.tour.domain.tour.TourId;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Schedule extends SoftDeletableAggregate<ScheduleId> {
    DepartureDate departureDate;
    ScheduleSeats seats;
    Pricing pricing;
    BigDecimal surcharge;
    ScheduleStatus status;
    TourId tourId;
    Long version;


    private Schedule(
            ScheduleId id,
            DepartureDate departureDate,
            ScheduleSeats seats,
            Pricing pricing,
            BigDecimal surcharge,
            ScheduleStatus status,
            TourId tourId
    ) {
        super(id);
        this.departureDate = departureDate;
        this.seats = seats;
        this.pricing = pricing;
        this.surcharge = surcharge;
        this.status = requireStatus(status);
        this.tourId = tourId;
    }

    private Schedule(
            ScheduleId id,
            DepartureDate departureDate,
            ScheduleSeats seats,
            Pricing pricing,
            BigDecimal surcharge,
            ScheduleStatus status,
            TourId tourId,
            Long version,
            Instant createdAt,
            Instant updatedAt,
            Instant deletedAt
    ) {
        super(id, createdAt, updatedAt, deletedAt);
        this.departureDate = departureDate;
        this.seats = seats;
        this.pricing = pricing;
        this.surcharge = surcharge;
        this.tourId = tourId;
        this.status = requireStatus(status);
        this.version = version;
    }

    public static Schedule create(
            DepartureDate departureDate,
            ScheduleSeats seats,
            Pricing pricing,
            BigDecimal surcharge,
            ScheduleStatus status,
            TourId tourId,
            Integer minParticipants
    ) {
        if(minParticipants != null && seats.total() < minParticipants) {
            throw new TotalSeatsLowerThanMinParticipantsException();
        }
        return new Schedule(
                ScheduleId.generate(),
                departureDate,
                seats,
                pricing,
                surcharge,
                status,
                tourId
        );
    }

    @Builder(builderMethodName = "fromExistingBuilder")
    public static Schedule fromExisting(
            ScheduleId id,
            DepartureDate departureDate,
            ScheduleSeats seats,
            Pricing pricing,
            BigDecimal surcharge,
            ScheduleStatus status,
            TourId tourId,
            Long version,
            Instant createdAt,
            Instant updatedAt,
            Instant deletedAt
    ) {
        return new Schedule(
                id,
                departureDate,
                seats,
                pricing,
                surcharge,
                status,
                tourId,
                version,
                createdAt,
                updatedAt,
                deletedAt
        );
    }

    public void updateDepartureDate(DepartureDate departureDate) {
        this.departureDate = departureDate;
        touch();
    }

    public void updatePricing(Pricing pricing) {
        this.pricing = pricing;
        touch();
    }

    public void updateSurcharge(BigDecimal surcharge) {
        this.surcharge = surcharge;
        touch();
    }

    public void updateStatus(ScheduleStatus status) {
        this.status = requireStatus(status);
        touch();
    }

    public void updateSeats(ScheduleSeats seats, Integer minParticipants) {
        this.seats = seats;
        checkValidSeats(minParticipants);
        touch();
    }

    public void updateVersion(Long version) {
        this.version = version;
        touch();
    }

    private void checkValidSeats(Integer minParticipants) {
        if (minParticipants != null && this.seats.total() < minParticipants) {
            throw new TotalSeatsLowerThanMinParticipantsException();
        }
    }

    private static ScheduleStatus requireStatus(ScheduleStatus status) {
        if (status == null) {
            throw new InvalidScheduleStatusException();
        }
        return status;
    }
}

