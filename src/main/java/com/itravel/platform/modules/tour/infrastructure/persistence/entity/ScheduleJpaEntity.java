package com.itravel.platform.modules.tour.infrastructure.persistence.entity;

import com.itravel.platform.common.infrastructure.SoftDeletableJpaBaseModel;
import com.itravel.platform.modules.tour.domain.aggregate.enums.ScheduleStatus;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    LocalDate departureDate;

    @Column(nullable = false)
    Integer availableSeats;

    @Column
    BigDecimal surcharge;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    ScheduleStatus status;
}

