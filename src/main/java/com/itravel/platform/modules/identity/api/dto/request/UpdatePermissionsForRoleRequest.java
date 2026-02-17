package com.itravel.platform.modules.identity.api.dto.request;

import java.util.List;

public record UpdatePermissionsForRoleRequest(Long id, List<String> permissionCodeList) {
}
