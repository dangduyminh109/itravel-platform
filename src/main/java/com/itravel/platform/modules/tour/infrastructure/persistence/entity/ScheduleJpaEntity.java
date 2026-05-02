package com.itravel.platform.modules.tour.infrastructure.persistence.entity;

import com.itravel.platform.common.infrastructure.SoftDeletableJpaBaseModel;
import com.itravel.platform.modules.tour.domain.schedule.ScheduleStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@SuperBuilder(toBuilder = true)
@Table(name = "schedule")
public class ScheduleJpaEntity extends SoftDeletableJpaBaseModel {
    @Id
    String id;

    @Column(nullable = false)
    LocalDate departureDate;

    @Column
    Integer totalSeats;

    @Column
    @Builder.Default
    Integer bookedSeats = 0;

    @Column
    @Builder.Default
    Integer lockedSeats = 0;

    @Column(precision = 19, scale = 2)
    BigDecimal surcharge;

    @Column(name = "currency_code")
    String currency;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "originalPrice", column = @Column(name = "adult_original_price")),
        @AttributeOverride(name = "discountPrice", column = @Column(name = "adult_discount_price"))
    })
    TicketPriceJpaEntity adultPrice;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "originalPrice", column = @Column(name = "child_original_price")),
        @AttributeOverride(name = "discountPrice", column = @Column(name = "child_discount_price"))
    })
    TicketPriceJpaEntity childPrice;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "originalPrice", column = @Column(name = "infant_original_price")),
        @AttributeOverride(name = "discountPrice", column = @Column(name = "infant_discount_price"))
    })
    TicketPriceJpaEntity infantPrice;

    @Column(name = "single_supplement_price")
    BigDecimal singleSupplement;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    ScheduleStatus status;

    String tourId;

    @Version
    Long version;
}

