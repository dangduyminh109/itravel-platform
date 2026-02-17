package com.itravel.platform.modules.identity.application.query;

import com.itravel.platform.modules.identity.domain.aggregate.Role;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.FullName;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.UserId;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.Username;
import lombok.Builder;
import java.time.Instant;
import java.util.Set;

@Builder
public record UserDetail(
    UserId id,
    Username username,
    FullName fullName,
    Set<Role> roleList,
    Instant createdAt,
    Instant updatedAt,
    Instant deletedAt
) {}
