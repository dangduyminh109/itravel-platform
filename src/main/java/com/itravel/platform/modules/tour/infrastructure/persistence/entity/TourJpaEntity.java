package com.itravel.platform.modules.tour.infrastructure.persistence.entity;

import com.itravel.platform.common.infrastructure.SoftDeletableJpaBaseModel;
import com.itravel.platform.modules.location.infrastructure.persistence.entity.LocationJpaEntity;
import com.itravel.platform.modules.tour.domain.tour.TourStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@SuperBuilder(toBuilder = true)
@Table(name = "tour")
public class TourJpaEntity extends SoftDeletableJpaBaseModel {
    @Id
    String id;

    @Column(unique = true, nullable = false)
    String name;

    @Column(unique = true, nullable = false)
    String slug;

    @Column(columnDefinition = "TEXT")
    String summary;

    @Column(columnDefinition = "LONGTEXT")
    String description;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    TourStatus status;

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

    @Column(name = "currency_code")
    String currency;

    @Column(name = "duration_days")
    Integer durationDays;

    @Column(name = "duration_nights")
    Integer durationNights;

    @Column(name = "limit_min")
    Integer minParticipants;

    @Column(name = "limit_max")
    Integer maxParticipants;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "services_includes", columnDefinition = "json")
    List<String> includedServices;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "services_excludes", columnDefinition = "json")
    List<String> excludedServices;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    CategoryJpaEntity category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "departure_location_id")
    LocationJpaEntity departureLocation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "destination_location_id")
    LocationJpaEntity destinationLocation;

    @OneToMany(mappedBy = "tour", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    List<ItineraryJpaEntity> itineraries = new ArrayList<>();

    @OneToMany(mappedBy = "tour", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    List<TourImageJpaEntity> tourImages = new ArrayList<>();
}