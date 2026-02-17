package com.itravel.platform.modules.identity.infrastructure.persistence.entity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Table(name = "Account_Link")
public class AccountLinkJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    String accountId;

    @Column(nullable = false)
    String targetType;

    @Column(nullable = false)
    String targetId;
}