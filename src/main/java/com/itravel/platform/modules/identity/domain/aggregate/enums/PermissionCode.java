package com.itravel.platform.modules.identity.domain.aggregate.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PACKAGE)
public enum PermissionCode {
    USER_VIEW("USER_VIEW", "View user"),
    USER_CREATE("USER_CREATE", "Create user"),
    USER_UPDATE("USER_UPDATE", "Update user"),
    USER_DELETE("USER_DELETE", "Delete user"),

    CUSTOMER_VIEW("CUSTOMER_VIEW", "View customer"),
    CUSTOMER_CREATE("CUSTOMER_CREATE", "Create customer"),
    CUSTOMER_UPDATE("CUSTOMER_UPDATE", "Update customer"),
    CUSTOMER_DELETE("CUSTOMER_DELETE", "Delete customer"),

    ROLE_VIEW("ROLE_VIEW", "View role"),
    ROLE_CREATE("ROLE_CREATE", "Create role"),
    ROLE_UPDATE("ROLE_UPDATE", "Update role"),
    ROLE_DELETE("ROLE_DELETE", "Delete role");

    String code;
    String description;
}
