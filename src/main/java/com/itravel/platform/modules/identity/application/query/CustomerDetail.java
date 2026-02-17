package com.itravel.platform.modules.identity.application.query;

import com.itravel.platform.modules.identity.domain.aggregate.Role;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.CustomerId;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.Email;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.FullName;
import lombok.Builder;
import java.time.Instant;
import java.util.Set;

@Builder
public record CustomerDetail(
    CustomerId id,
    FullName fullName,
    Email email,
    Set<Role> roleList,
    Instant createdAt,
    Instant updatedAt,
    Instant deletedAt
) {}
