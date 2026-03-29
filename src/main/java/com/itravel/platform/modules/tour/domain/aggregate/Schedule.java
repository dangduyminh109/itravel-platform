package com.itravel.platform.modules.tour.domain.aggregate;

import com.itravel.platform.common.domain.SoftDeletableAggregate;
import com.itravel.platform.modules.tour.domain.aggregate.enums.ScheduleStatus;
import com.itravel.platform.modules.tour.domain.aggregate.valueobject.AvailableSeats;
import com.itravel.platform.modules.tour.domain.aggregate.valueobject.DepartureDate;
import com.itravel.platform.modules.tour.domain.aggregate.valueobject.ScheduleId;
import com.itravel.platform.modules.tour.domain.exception.InvalidScheduleStatusException;
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
    AvailableSeats availableSeats;
    BigDecimal surcharge;
    ScheduleStatus status;

    private Schedule(
            ScheduleId id,
            DepartureDate departureDate,
            AvailableSeats availableSeats,
            BigDecimal surcharge,
            ScheduleStatus status
    ) {
        super(id);
        this.departureDate = departureDate;
        this.availableSeats = availableSeats;
        this.surcharge = surcharge;
        this.status = requireStatus(status);
    }

    private Schedule(
            ScheduleId id,
            DepartureDate departureDate,
            AvailableSeats availableSeats,
            BigDecimal surcharge,
            ScheduleStatus status,
            Instant createdAt,
            Instant updatedAt,
            Instant deletedAt
    ) {
        super(id, createdAt, updatedAt, deletedAt);
        this.departureDate = departureDate;
        this.availableSeats = availableSeats;
        this.surcharge = surcharge;
        this.status = requireStatus(status);
    }

    public static Schedule create(
            DepartureDate departureDate,
            AvailableSeats availableSeats,
            BigDecimal surcharge,
            ScheduleStatus status
    ) {
        return new Schedule(
                null,
                departureDate,
                availableSeats,
                surcharge,
                status
        );
    }

    @Builder(builderMethodName = "fromExistingBuilder")
    public static Schedule fromExisting(
            ScheduleId id,
            DepartureDate departureDate,
            AvailableSeats availableSeats,
            BigDecimal surcharge,
            ScheduleStatus status,
            Instant createdAt,
            Instant updatedAt,
            Instant deletedAt
    ) {
        return new Schedule(
                id,
                departureDate,
                availableSeats,
                surcharge,
                status,
                createdAt,
                updatedAt,
                deletedAt
        );
    }

    public void updateDepartureDate(DepartureDate departureDate) {
        this.departureDate = departureDate;
        touch();
    }

    public void updateAvailableSeats(AvailableSeats availableSeats) {
        this.availableSeats = availableSeats;
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

    private static ScheduleStatus requireStatus(ScheduleStatus status) {
        if (status == null) {
            throw new InvalidScheduleStatusException();
        }
        return status;
    }
}

