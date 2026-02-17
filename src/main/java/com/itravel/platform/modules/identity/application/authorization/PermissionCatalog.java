package com.itravel.platform.modules.identity.application.authorization;

import com.itravel.platform.modules.identity.domain.aggregate.valueobject.Permission;

import java.util.List;

public final class PermissionCatalog {
    public static final Permission USER_VIEW =
            new Permission("USER_VIEW");

    public static final Permission USER_CREATE =
            new Permission("USER_CREATE");

    public static final Permission USER_UPDATE =
            new Permission("USER_UPDATE");

    public static final Permission USER_DELETE =
            new Permission("USER_DELETE");

    private PermissionCatalog() {}

    public static List<Permission> getPermissionList() {
        return List.of(
                USER_VIEW,
                USER_CREATE,
                USER_UPDATE,
                USER_DELETE
        );
    }

    public static boolean checkPermissionCode(Permission code) {
        return getPermissionList()
                .stream()
                .anyMatch(item -> item.equals(code));
    }
}
