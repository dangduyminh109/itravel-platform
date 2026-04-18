package com.itravel.platform.modules.identity.domain.role;

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
    ROLE_DELETE("ROLE_DELETE", "Delete role", "ROLE"),

    LOCATION_VIEW("LOCATION_VIEW", "View location", "LOCATION"),
    LOCATION_CREATE("LOCATION_CREATE", "Create location", "LOCATION"),
    LOCATION_UPDATE("LOCATION_UPDATE", "Update location", "LOCATION"),
    LOCATION_DELETE("LOCATION_DELETE", "Delete location", "LOCATION"),

    CATEGORY_VIEW("CATEGORY_VIEW", "View category", "CATEGORY"),
    CATEGORY_CREATE("CATEGORY_CREATE", "Create category", "CATEGORY"),
    CATEGORY_UPDATE("CATEGORY_UPDATE", "Update category", "CATEGORY"),
    CATEGORY_DELETE("CATEGORY_DELETE", "Delete category", "CATEGORY"),

    TOUR_VIEW("TOUR_VIEW", "View tour", "TOUR"),
    TOUR_CREATE("TOUR_CREATE", "Create tour", "TOUR"),
    TOUR_UPDATE("TOUR_UPDATE", "Update tour", "TOUR"),
    TOUR_DELETE("TOUR_DELETE", "Delete tour", "TOUR"),

    SCHEDULE_VIEW("SCHEDULE_VIEW", "View tour", "SCHEDULE"),
    SCHEDULE_CREATE("SCHEDULE_CREATE", "Create tour", "SCHEDULE"),
    SCHEDULE_UPDATE("SCHEDULE_UPDATE", "Update tour", "SCHEDULE"),
    SCHEDULE_DELETE("SCHEDULE_DELETE", "Delete tour", "SCHEDULE")
    ;

    String code;
    String description;
    String group;
}

