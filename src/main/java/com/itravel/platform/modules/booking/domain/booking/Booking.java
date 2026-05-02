package com.itravel.platform.modules.booking.domain.booking;

import com.itravel.platform.common.domain.SoftDeletableAggregate;
import com.itravel.platform.common.domain.enums.CurrencyCode;
import com.itravel.platform.modules.booking.domain.bookingItem.BookingItem;
import com.itravel.platform.modules.booking.domain.bookingItem.ServiceType;
import com.itravel.platform.modules.booking.domain.event.BookingCancelledEvent;
import com.itravel.platform.modules.booking.domain.event.BookingCreatedEvent;
import com.itravel.platform.modules.booking.domain.event.BookingExpiredEvent;
import com.itravel.platform.modules.booking.domain.event.BookingPaidEvent;
import com.itravel.platform.modules.booking.domain.exception.BookingCannotCancelCompletedException;
import com.itravel.platform.modules.booking.domain.exception.BookingCannotModifyException;
import com.itravel.platform.modules.booking.domain.exception.InvalidBookingStateTransitionException;
import com.itravel.platform.modules.booking.domain.passenger.Passenger;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import com.itravel.platform.common.domain.aggregate.valueobject.Money;
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
    Money totalAmount;
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
        this.totalAmount = Money.zero(CurrencyCode.VND);
        this.expiredAt = null;
    }

    private Booking(
            BookingId id,
            BookingCode bookingCode,
            String customerId,
            ContactInfo contactInfo,
            String note,
            BookingStatus status,
            Money totalAmount,
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
            String note,
            List<BookingItem> bookingItems,
            List<Passenger> passengers
    ) {
        BookingCode code = BookingCode.generate(serviceType);
        Booking booking = new Booking(code, customerId, contactInfo, note);

        if (bookingItems != null) {
            booking.bookingItems.addAll(bookingItems);
        }
        if (passengers != null) {
            booking.passengers.addAll(passengers);
        }

        booking.calculateTotalAmount();

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
            Money totalAmount,
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
        if (bookingItems.isEmpty()) return;
        Money first = bookingItems.get(0).getSubtotal();
        this.totalAmount = bookingItems.stream()
                .map(BookingItem::getSubtotal)
                .skip(1)
                .reduce(first, Money::add);
        touch();
    }

    public void markAsReserved(int expirationMinutes) {
        assertStatus(BookingStatus.PENDING);
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
                totalAmount.getAmount(), 
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
        assertStatus(BookingStatus.PAID);
        this.status = BookingStatus.COMPLETED;
        touch();
    }

    public void expire() {
        assertStatus(BookingStatus.RESERVED);
        this.status = BookingStatus.EXPIRED;
        this.expiredAt = null;
        touch();
        registerEvent(new BookingExpiredEvent(
                getId().value(),
                bookingCode.value(),
                getUpdatedAt()
        ));
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

    private void assertStatus(BookingStatus expected) {
        if (status != expected) {
            throw new InvalidBookingStateTransitionException();
        }
    }
}
