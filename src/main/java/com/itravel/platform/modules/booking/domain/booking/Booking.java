package com.itravel.platform.modules.booking.domain.booking;

import com.itravel.platform.common.domain.SoftDeletableAggregate;
import com.itravel.platform.common.exceptions.DomainErrorCode;
import com.itravel.platform.modules.booking.domain.bookingItem.BookingItem;
import com.itravel.platform.modules.booking.domain.bookingItem.ServiceType;
import com.itravel.platform.modules.booking.domain.event.BookingCancelledEvent;
import com.itravel.platform.modules.booking.domain.event.BookingCreatedEvent;
import com.itravel.platform.modules.booking.domain.event.BookingPaidEvent;
import com.itravel.platform.modules.booking.domain.exception.BookingCannotCancelCompletedException;
import com.itravel.platform.modules.booking.domain.exception.BookingCannotModifyException;
import com.itravel.platform.modules.booking.domain.exception.InvalidBookingStateTransitionException;
import com.itravel.platform.modules.booking.domain.passenger.Passenger;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Booking extends SoftDeletableAggregate<BookingId> {
    BookingCode bookingCode;
    String customerId;
    ContactInfo contactInfo;
    String note;
    BookingStatus status;
    BigDecimal totalAmount;
    Instant expiredAt;
    final List<BookingItem> bookingItems = new ArrayList<>();
    final List<Passenger> passengers = new ArrayList<>();

    private Booking(
            BookingCode bookingCode,
            String customerId,
            ContactInfo contactInfo,
            String note
    ) {
        super(BookingId.generate());
        this.bookingCode = bookingCode;
        this.customerId = customerId;
        this.contactInfo = contactInfo;
        this.note = note;
        this.status = BookingStatus.PENDING;
        this.totalAmount = BigDecimal.ZERO;
        this.expiredAt = null;
    }

    private Booking(
            BookingId id,
            BookingCode bookingCode,
            String customerId,
            ContactInfo contactInfo,
            String note,
            BookingStatus status,
            BigDecimal totalAmount,
            Instant expiredAt,
            Instant createdAt,
            Instant updatedAt,
            Instant deletedAt
    ) {
        super(id, createdAt, updatedAt, deletedAt);
        this.bookingCode = bookingCode;
        this.customerId = customerId;
        this.contactInfo = contactInfo;
        this.note = note;
        this.status = status;
        this.totalAmount = totalAmount;
        this.expiredAt = expiredAt;
    }

    public static Booking create(
            ContactInfo contactInfo,
            ServiceType serviceType,
            String customerId,
            String note
    ) {
        BookingCode code = BookingCode.generate(serviceType);
        Booking booking = new Booking(code, customerId, contactInfo, note);
        booking.registerEvent(new BookingCreatedEvent(
                booking.getId().value(),
                code.value(),
                booking.getCreatedAt()
        ));
        return booking;
    }

    @Builder(builderMethodName = "fromExistingBuilder")
    public static Booking fromExisting(
            BookingId id,
            BookingCode bookingCode,
            String customerId,
            ContactInfo contactInfo,
            String note,
            BookingStatus status,
            BigDecimal totalAmount,
            Instant expiredAt,
            Instant createdAt,
            Instant updatedAt,
            Instant deletedAt,
            List<BookingItem> bookingItems,
            List<Passenger> passengers
    ) {
        Booking booking = new Booking(
                id, bookingCode, customerId, contactInfo, note,
                status, totalAmount, expiredAt,
                createdAt, updatedAt, deletedAt
        );
        if (bookingItems != null) {
            booking.bookingItems.addAll(bookingItems);
        }
        if (passengers != null) {
            booking.passengers.addAll(passengers);
        }
        return booking;
    }

    public void addBookingItem(BookingItem item) {
        assertModifiable();
        this.bookingItems.add(item);
        touch();
    }

    public void addPassenger(Passenger passenger) {
        assertModifiable();
        this.passengers.add(passenger);
        touch();
    }

    public void calculateTotalAmount() {
        this.totalAmount = bookingItems.stream()
                .map(BookingItem::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        touch();
    }

    public void markAsReserved(int expirationMinutes) {
        assertStatus(BookingStatus.PENDING, "markAsReserved");
        this.status = BookingStatus.RESERVED;
        this.expiredAt = Instant.now().plusSeconds((long) expirationMinutes * 60);
        touch();
    }

    public void markAsPaid() {
        if (status != BookingStatus.PENDING && status != BookingStatus.RESERVED) {
            throw new InvalidBookingStateTransitionException();
        }
        this.status = BookingStatus.PAID;
        this.expiredAt = null;
        touch();
        registerEvent(new BookingPaidEvent(
                getId().value(),
                bookingCode.value(),
                totalAmount,
                getUpdatedAt()
        ));
    }

    public void cancel() {
        if (status == BookingStatus.COMPLETED || status == BookingStatus.CANCELLED) {
            throw new BookingCannotCancelCompletedException();
        }
        this.status = BookingStatus.CANCELLED;
        this.expiredAt = null;
        touch();
        registerEvent(new BookingCancelledEvent(
                getId().value(),
                bookingCode.value(),
                getUpdatedAt()
        ));
    }

    public void complete() {
        assertStatus(BookingStatus.PAID, "complete");
        this.status = BookingStatus.COMPLETED;
        touch();
    }

    public void expire() {
        assertStatus(BookingStatus.RESERVED, "expire");
        this.status = BookingStatus.EXPIRED;
        this.expiredAt = null;
        touch();
    }

    public List<BookingItem> getBookingItems() {
        return Collections.unmodifiableList(bookingItems);
    }

    public List<Passenger> getPassengers() {
        return Collections.unmodifiableList(passengers);
    }

    private void assertModifiable() {
        if (status != BookingStatus.PENDING) {
            throw new BookingCannotModifyException();
        }
    }

    private void assertStatus(BookingStatus expected, String operation) {
        if (status != expected) {
            throw new InvalidBookingStateTransitionException();
        }
    }
}
