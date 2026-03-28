package com.itravel.platform.modules.tour.infrastructure.persistence.entity;

import com.itravel.platform.common.infrastructure.SoftDeletableJpaBaseModel;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@SuperBuilder(toBuilder = true)
@Table(name = "itinerary")
public class ItineraryJpaEntity extends SoftDeletableJpaBaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    Integer dayNumber;

    @Column(nullable = false, length = 200)
    String title;

    @Column(columnDefinition = "TEXT")
    String description;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "itinerary_activity", joinColumns = @JoinColumn(name = "itinerary_id"))
    @Column(name = "activity", nullable = false)
    @OrderColumn(name = "activity_order")
    List<String> activities = new ArrayList<>();
}
