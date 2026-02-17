package com.itravel.platform.modules.identity.infrastructure.persistence.entity;
import com.itravel.platform.common.infrastructure.JpaBaseModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import java.time.Instant;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@SuperBuilder(toBuilder = true)
@Table(name = "Account")
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
}
