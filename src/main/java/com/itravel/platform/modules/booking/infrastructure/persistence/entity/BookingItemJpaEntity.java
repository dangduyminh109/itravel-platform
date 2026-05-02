package com.itravel.platform.modules.booking.infrastructure.persistence.entity;

import com.itravel.platform.common.infrastructure.JpaBaseModel;
import com.itravel.platform.modules.booking.domain.bookingItem.ServiceType;
import com.itravel.platform.modules.booking.domain.bookingItem.ServiceSnapshot;
import com.itravel.platform.modules.booking.domain.bookingItem.PriceLine;
import jakarta.persistence.*;
import java.util.List;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "booking_items")
public class BookingItemJpaEntity extends JpaBaseModel {

    @Id
    @Column(nullable = false)
    String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "booking_id", nullable = false)
    BookingJpaEntity booking;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    ServiceType serviceType;

    @Column(nullable = false)
    String referenceId;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "json")
    ServiceSnapshot snapshotData;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "json")
    List<PriceLine> priceBreakdown;
}
