package com.itravel.platform.modules.identity.infrastructure.persistence.entity;

import com.itravel.platform.common.infrastructure.SoftDeletableJpaBaseModel;
import com.itravel.platform.common.domain.enums.Gender;
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
@Table(name = "Customer")
public class CustomerJpaEntity extends SoftDeletableJpaBaseModel {
    @Id
    String id;

    @Column(nullable = false)
    String fullName;

    @Column
    String phoneNumber;

    @Column
    String avatar;

    @Enumerated(EnumType.STRING)
    @Column
    Gender gender;

    @Column
    LocalDate dateOfBirth;

    @Column
    String addressDetail;

    @Column
    Long wardId;

    @Column
    Long provinceId;

    @Column
    String identityCardNumber;

    @Column
    LocalDate identityCardIssueDate;

    @Column
    String identityCardIssuePlace;

    @Column
    String passportNumber;

    @Column
    LocalDate passportIssueDate;

    @Column
    LocalDate passportExpiryDate;
}