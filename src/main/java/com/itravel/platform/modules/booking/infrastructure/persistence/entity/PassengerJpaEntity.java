package com.itravel.platform.modules.booking.infrastructure.persistence.entity;

import com.itravel.platform.common.infrastructure.JpaBaseModel;
import com.itravel.platform.modules.booking.domain.passenger.PassengerType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "passengers")
public class PassengerJpaEntity extends JpaBaseModel {
    @Id
    String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "booking_id", nullable = false)
    BookingJpaEntity booking;

    @Column(nullable = false)
    String fullName;

    @Column(nullable = false)
    LocalDate dateOfBirth;

    @Column(nullable = false)
    String gender;

    String identityNumber;

    @Column(name = "passenger_type", nullable = false)
    @Enumerated(EnumType.STRING)
    PassengerType passengerType;
}
