package com.itravel.platform.modules.identity.infrastructure.persistence.entity;

import com.itravel.platform.common.infrastructure.JpaBaseModel;
import com.itravel.platform.modules.identity.domain.aggregate.enums.AccountStatus;
import com.itravel.platform.modules.identity.domain.aggregate.enums.AuthProvider;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.time.Instant;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@SuperBuilder(toBuilder = true)
@Table(name = "otp")
public class OtpJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    String email;

    @Column(nullable = false)
    String code;

    @Column(nullable = false)
    Instant expiresAt;
}
