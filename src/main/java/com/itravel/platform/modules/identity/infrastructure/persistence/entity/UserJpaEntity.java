package com.itravel.platform.modules.identity.infrastructure.persistence.entity;

import com.itravel.platform.common.infrastructure.JpaBaseModel;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import java.util.Set;

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

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "userId"),
            inverseJoinColumns = @JoinColumn(name = "roleId")
    )
    Set<RoleJpaEntity> roleList;
}

