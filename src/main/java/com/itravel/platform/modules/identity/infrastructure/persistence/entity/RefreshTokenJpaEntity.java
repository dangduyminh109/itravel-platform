package com.itravel.platform.modules.identity.infrastructure.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Table(name = "refresh_token")
public class RefreshTokenJpaEntity {
    @Id
    String id;

    @Column(nullable = false)
    String accountId;

    @Column(nullable = false)
    String tokenHash;

    @Column(nullable = false)
    Instant expiresAt;

    Instant revokedAt;
    Instant createdAt;
}

