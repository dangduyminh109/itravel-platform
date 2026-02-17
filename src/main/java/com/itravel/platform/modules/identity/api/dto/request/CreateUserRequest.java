package com.itravel.platform.modules.identity.api.dto.request;

import java.util.Set;

public record CreateUserRequest(
        String username,
        String password,
        String fullName,
        Set<Long> roleList
) { }

