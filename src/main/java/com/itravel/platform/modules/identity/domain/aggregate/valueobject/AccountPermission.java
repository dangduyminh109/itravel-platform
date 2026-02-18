package com.itravel.platform.modules.identity.domain.aggregate.valueobject;
import com.itravel.platform.modules.identity.domain.aggregate.enums.PermissionType;

public record AccountPermission(Permission permission, PermissionType type) {
}
