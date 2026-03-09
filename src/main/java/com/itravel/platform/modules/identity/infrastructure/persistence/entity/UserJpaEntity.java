package com.itravel.platform.modules.identity.infrastructure.persistence.entity;

import com.itravel.platform.common.infrastructure.JpaBaseModel;
import com.itravel.platform.modules.identity.domain.aggregate.enums.Gender;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@SuperBuilder(toBuilder = true)
@Table(name = "user")
public class UserJpaEntity extends JpaBaseModel {
    @Id
    String id;

    @Column(nullable = false)
    String fullName;

    String email;

    @Column
    String phoneNumber;

    @Column
    String avatar;

    @Enumerated(EnumType.STRING)
    @Column
    Gender gender;

    @Column
    LocalDate dateOfBirth;
}

