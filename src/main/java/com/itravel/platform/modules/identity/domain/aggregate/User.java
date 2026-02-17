package com.itravel.platform.modules.identity.domain.aggregate;

import com.itravel.platform.common.domain.BaseAggregate;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.FullName;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.UserId;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User extends BaseAggregate<UserId> {
    FullName fullName;
    final Set<Role> roleList = new HashSet<>();

    private User(
            FullName fullName
    ) {
        super(UserId.generate());
        this.fullName = fullName;
    }

    private User(UserId id,
                 FullName fullName,
                 Instant createdAt,
                 Instant updatedAt,
                 Instant deletedAt
    ) {
        super(id);
        this.fullName = fullName;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
    }

    public static User create(
            FullName fullName
    ) {
        return new User(
                fullName
        );
    }

    @Builder(builderMethodName = "fromExistingBuilder")
    public static User fromExisting(UserId id,
                                    FullName fullName,
                                    Instant createdAt,
                                    Instant updatedAt,
                                    Instant deletedAt,
                                    Set<Role> roleList
    ) {
        User u =  new User(
                id,
                fullName,
                createdAt,
                updatedAt,
                deletedAt
        );
        u.roleList.addAll(roleList);
        return u;
    }
    public void updateName(FullName fullName) {
        this.fullName = fullName;
        touch();
    }

    public void grantRole(Role role) {
        this.roleList.add(role);
    }

    public void revokeRole(Role role) {
        this.roleList.remove(role);
    }
}
