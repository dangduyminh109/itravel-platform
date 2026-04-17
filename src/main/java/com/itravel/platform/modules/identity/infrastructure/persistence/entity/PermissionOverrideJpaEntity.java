package com.itravel.platform.modules.identity.infrastructure.persistence.entity;
import com.itravel.platform.modules.identity.domain.role.PermissionType;
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
@Table(name = "permission_overrides")
public class PermissionOverrideJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    String accountId;

    @Column(nullable = false)
    PermissionType permissionType;

    @Column(nullable = false)
    String permission;

    @ManyToOne
    @JoinColumn(name = "accountId", insertable = false, updatable = false)
    AccountJpaEntity account;
}