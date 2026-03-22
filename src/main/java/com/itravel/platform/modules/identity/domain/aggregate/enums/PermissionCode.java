package com.itravel.platform.modules.identity.domain.aggregate.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PACKAGE)
public enum PermissionCode {
    USER_VIEW("USER_VIEW", "View user", "USER"),
    USER_CREATE("USER_CREATE", "Create user", "USER"),
    USER_UPDATE("USER_UPDATE", "Update user", "USER"),
    USER_DELETE("USER_DELETE", "Delete user", "USER"),

    CUSTOMER_VIEW("CUSTOMER_VIEW", "View customer", "CUSTOMER"),
    CUSTOMER_CREATE("CUSTOMER_CREATE", "Create customer", "CUSTOMER"),
    CUSTOMER_UPDATE("CUSTOMER_UPDATE", "Update customer", "CUSTOMER"),
    CUSTOMER_DELETE("CUSTOMER_DELETE", "Delete customer", "CUSTOMER"),

    ROLE_VIEW("ROLE_VIEW", "View role", "ROLE"),
    ROLE_CREATE("ROLE_CREATE", "Create role", "ROLE"),
    ROLE_UPDATE("ROLE_UPDATE", "Update role", "ROLE"),
    ROLE_DELETE("ROLE_DELETE", "Delete role", "ROLE");

    String code;
    String description;
    String group;
}
