package com.itravel.platform.modules.tour.infrastructure.persistence.entity;

import com.itravel.platform.common.infrastructure.SoftDeletableJpaBaseModel;
import com.itravel.platform.modules.tour.domain.aggregate.enums.CategoryStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@SuperBuilder(toBuilder = true)
@Table(name = "category")
public class CategoryJpaEntity extends SoftDeletableJpaBaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false, unique = true)
    String name;

    @Column(nullable = false)
    CategoryStatus status;

    @Column(nullable = false)
    String slug;

    @Column(columnDefinition = "TEXT")
    String description;
}
