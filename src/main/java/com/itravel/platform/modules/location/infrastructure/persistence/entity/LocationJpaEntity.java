package com.itravel.platform.modules.location.infrastructure.persistence.entity;

import com.itravel.platform.common.infrastructure.JpaBaseModel;
import com.itravel.platform.modules.location.domain.aggregate.enums.LocationStatus;
import com.itravel.platform.modules.location.domain.aggregate.enums.LocationType;
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
@Table(name = "location")
public class LocationJpaEntity extends JpaBaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    String name;

    @Column
    LocationStatus status;

    @Column
    LocationType type;

    @Column
    String slug;

    @OneToMany(
            mappedBy = "parent",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            fetch = FetchType.LAZY
    )
    List<LocationJpaEntity> children = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "parent_id")
    LocationJpaEntity parent;
}

