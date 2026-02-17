package com.itravel.platform.modules.identity.api.dto.response;

import java.util.Set;

public record RoleResponse(Long id, String name, String status, Set<String> permissionList) {
}
