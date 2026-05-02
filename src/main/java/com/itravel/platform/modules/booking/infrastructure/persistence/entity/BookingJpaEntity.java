package com.itravel.platform.modules.booking.infrastructure.persistence.entity;

import com.itravel.platform.common.infrastructure.SoftDeletableJpaBaseModel;
import com.itravel.platform.modules.booking.domain.booking.BookingStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "bookings")
public class BookingJpaEntity extends SoftDeletableJpaBaseModel {

    @Id
    String id;

    @Column(nullable = false, unique = true)
    String bookingCode;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    BookingStatus status;

    @Column(nullable = false)
    Double totalAmount;

    @Column(nullable = false)
    String currency;

    String customerId;

    @Column(nullable = false)
    String contactFullName;

    @Column(nullable = false)
    String contactEmail;

    @Column(nullable = false)
    String contactPhone;

    String contactAddress;

    String note;

    Instant expiredAt;

    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    List<BookingItemJpaEntity> bookingItems = new ArrayList<>();

    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    List<PassengerJpaEntity> passengers = new ArrayList<>();
}
