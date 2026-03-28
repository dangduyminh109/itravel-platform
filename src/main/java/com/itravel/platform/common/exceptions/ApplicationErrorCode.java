package com.itravel.platform.common.exceptions;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PACKAGE)
public enum ApplicationErrorCode {

    // ===== IDENTITY - ROLE =====
    ROLE_EXISTED("ROLE_EXISTED", "Role already exists", HttpStatus.BAD_REQUEST, null),
    ROLE_NOT_EXIST("ROLE_NOT_EXIST", "Role does not exist", HttpStatus.BAD_REQUEST, null),
    ROLE_INACTIVE("ROLE_INACTIVE", "Role is inactive", HttpStatus.BAD_REQUEST, "roleStatus"),
    ADMIN_AND_CUSTOMER_ROLE_CAN_NOT_DELETE("ADMIN_AND_CUSTOMER_ROLE_CAN_NOT_DELETE", "Admin and Customer role cannot be deleted", HttpStatus.FORBIDDEN, null),

    // ===== IDENTITY - USER =====
    USERNAME_EXISTED("USERNAME_EXISTED", "Username already exists", HttpStatus.BAD_REQUEST, "username"),
    USERNAME_OR_PASSWORD_INVALID("USERNAME_OR_PASSWORD_INVALID", "Invalid username or password", HttpStatus.UNAUTHORIZED, "username"),
    EMAIL_OR_PASSWORD_INVALID("EMAIL_OR_PASSWORD_INVALID", "Invalid email or password", HttpStatus.UNAUTHORIZED, "email"),
    EMAIL_EXISTED("EMAIL_EXISTED", "Email already exists", HttpStatus.BAD_REQUEST, "email"),
    USER_NOT_EXIST("USER_NOT_EXIST", "User does not exist", HttpStatus.BAD_REQUEST, null),
    USER_INACTIVE("USER_INACTIVE", "Account is inactive", HttpStatus.FORBIDDEN, "status"),
    USER_DELETED("USER_DELETED", "Account has been deleted", HttpStatus.UNAUTHORIZED, "deletedAt"),
    USER_NOT_DELETE_OR_UPDATE("USER_NOT_DELETE_OR_UPDATE", "User cannot be deleted or updated", HttpStatus.BAD_REQUEST, null),

    // ===== IDENTITY - CUSTOMER =====
    CUSTOMER_NOT_EXIST("CUSTOMER_NOT_EXIST", "Customer does not exist", HttpStatus.BAD_REQUEST, null),
    CUSTOMER_DELETED("CUSTOMER_DELETED", "Customer has been deleted", HttpStatus.UNAUTHORIZED, "deletedAt"),

    // ===== IDENTITY - ACCOUNT =====
    ACCOUNT_NOT_EXIST("ACCOUNT_NOT_EXIST", "Account does not exist", HttpStatus.BAD_REQUEST, null),
    ACCOUNT_INACTIVE("ACCOUNT_INACTIVE", "Account is inactive", HttpStatus.BAD_REQUEST, "status"),
    ACCOUNT_DELETED("ACCOUNT_DELETED", "Account has been deleted", HttpStatus.UNAUTHORIZED, "deletedAt"),

    // ===== IDENTITY - AUTHENTICATION =====
    INVALID_LOGIN_METHOD("INVALID_LOGIN_METHOD", "Invalid login method for this account", HttpStatus.BAD_REQUEST, "authProvider"),
    REFRESH_TOKEN_REVOKED("REFRESH_TOKEN_REVOKED", "Refresh token has been revoked", HttpStatus.UNAUTHORIZED, "refreshToken"),
    REFRESH_TOKEN_NOT_FOUND("REFRESH_TOKEN_NOT_FOUND", "Refresh token not found", HttpStatus.UNAUTHORIZED, "refreshToken"),
    REFRESH_TOKEN_EXPIRED("REFRESH_TOKEN_EXPIRED", "Refresh token has expired", HttpStatus.UNAUTHORIZED, "refreshToken"),
    TOKEN_INVALID("TOKEN_INVALID", "Invalid or expired token", HttpStatus.UNAUTHORIZED, "token"),
    PASSWORD_CONFIRMATION_MISMATCH("PASSWORD_CONFIRMATION_MISMATCH", "New password and confirm password do not match", HttpStatus.BAD_REQUEST, "confirmPassword"),
    INVALID_FIREBASE_TOKEN("INVALID_FIREBASE_TOKEN", "Invalid firebase token", HttpStatus.BAD_REQUEST, "authProvider"),

    // ===== LOCATION =====
    LOCATION_NAME_EXISTED("LOCATION_NAME_EXISTED", "location name already exists", HttpStatus.BAD_REQUEST, "name"),
    LOCATION_NOT_EXITS("LOCATION_NOT_EXITS", "location is not exists", HttpStatus.BAD_REQUEST, "name"),

    // ===== TOUR - CATEGORY =====
    CATEGORY_NAME_EXISTED("CATEGORY_NAME_EXISTED", "category name already exists", HttpStatus.BAD_REQUEST, "name"),
    CATEGORY_NOT_EXIST("CATEGORY_NOT_EXIST", "category does not exist", HttpStatus.BAD_REQUEST, "name"),
    ;

    String code;
    String message;
    HttpStatus httpStatusCode;
    String field;
}
