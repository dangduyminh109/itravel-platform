package com.itravel.platform.modules.identity.api.dto.request;

import java.util.Set;

public record UpdateUserRequest(
        String newPassword,
        String status,
        String fullName,
        Set<Long> roleList
) { }
