package com.itravel.platform.modules.identity.infrastructure.persistence.entity;
import com.itravel.platform.common.infrastructure.JpaBaseModel;
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
@Table(name = "account")
public class AccountJpaEntity extends JpaBaseModel {
    @Id
    String id;

    @Column(unique = true)
    String username;

    String password;

    @Column(unique = true)
    String email;

    @Column(nullable = false)
    String authProvider;

    Instant createdAt;
    Instant updatedAt;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "account_role",
            joinColumns = @JoinColumn(name = "accountId"),
            inverseJoinColumns = @JoinColumn(name = "roleId")
    )
    Set<RoleJpaEntity> roleList;

    @OneToMany(mappedBy = "accountId", cascade = CascadeType.ALL, orphanRemoval = true)
    Set<PermissionOverrideJpaEntity> permissionOverrides;
}
