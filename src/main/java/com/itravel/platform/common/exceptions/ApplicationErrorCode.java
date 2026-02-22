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

    // ===== AUTH / USER / CUSTOMER =====
    ROLE_EXISTED("ROLE_EXISTED", "Role already exists", HttpStatus.BAD_REQUEST, null),
    ROLE_NOT_EXIST("ROLE_NOT_EXIST", "Role does not exist", HttpStatus.BAD_REQUEST, null),
    ROLE_INACTIVE("ROLE_INACTIVE", "Role is InActive", HttpStatus.BAD_REQUEST, "role_status"),
    ADMIN_ROLE_CAN_NOT_DELETE("ADMIN_ROLE_CAN_NOT_DELETE", "Admin role cannot be deleted", HttpStatus.FORBIDDEN, null),

    USERNAME_EXISTED("USERNAME_EXISTED", "Username already exists", HttpStatus.BAD_REQUEST, "username"),
    USERNAME_OR_PASSWORD_INVALID("USERNAME_OR_PASSWORD_INVALID", "Invalid username or password", HttpStatus.UNAUTHORIZED, "username, password"),
    EMAIL_OR_PASSWORD_INVALID("EMAIL_OR_PASSWORD_INVALID", "Invalid email or password", HttpStatus.UNAUTHORIZED, "username, password"),
    EMAIL_EXISTED("EMAIL_EXISTED", "Email already exists", HttpStatus.BAD_REQUEST, "email"),

    USER_NOT_EXIST("USER_NOT_EXIST", "User does not exist", HttpStatus.BAD_REQUEST, null),
    USER_INACTIVE("USER_INACTIVE", "Account is inactive", HttpStatus.FORBIDDEN, "status"),
    USER_DELETED("USER_DELETED", "Account has been deleted", HttpStatus.UNAUTHORIZED, "deletedAt"),
    USER_NOT_DELETE_OR_UPDATE("USER_NOT_DELETE_OR_UPDATE", "User can not delete or update", HttpStatus.BAD_REQUEST, null),

    CUSTOMER_NOT_EXIST("CUSTOMER_NOT_EXIST", "Customer does not exist", HttpStatus.BAD_REQUEST, null),
    CUSTOMER_DELETED("CUSTOMER_DELETED", "Customer has been deleted", HttpStatus.UNAUTHORIZED, "deletedAt"),
    ACCOUNT_NOT_EXIST("ACCOUNT_NOT_EXIST", "Account does not exist", HttpStatus.BAD_REQUEST, null),
    ACCOUNT_INACTIVE("ACCOUNT_INACTIVE", "Account is inactive", HttpStatus.BAD_REQUEST, "status"),
    ACCOUNT_DELETED("ACCOUNT_DELETED", "Account has been deleted", HttpStatus.UNAUTHORIZED, "deletedAt"),

    INVALID_LOGIN_METHOD("INVALID_LOGIN_METHOD", "Invalid login method for this account", HttpStatus.BAD_REQUEST, "authProvider"),
    REFRESH_TOKEN_REVOKED("REFRESH_TOKEN_REVOKED", "Refresh token has been revoked", HttpStatus.UNAUTHORIZED, "refreshToken"),
    REFRESH_TOKEN_NOT_FOUND("REFRESH_TOKEN_NOT_FOUND", "Refresh token not found", HttpStatus.UNAUTHORIZED, "refreshToken"),
    REFRESH_TOKEN_EXPIRED("REFRESH_TOKEN_EXPIRED", "Refresh token has expired", HttpStatus.UNAUTHORIZED, "refreshToken"),
    TOKEN_INVALID("TOKEN_INVALID", "Invalid or expired token", HttpStatus.UNAUTHORIZED, "token"),
    PASSWORD_CONFIRMATION_MISMATCH("PASSWORD_CONFIRMATION_MISMATCH", "New password and confirm password do not match", HttpStatus.BAD_REQUEST, "confirmPassword")
    ;

    String code;
    String message;
    HttpStatus httpStatusCode;
    String feild;
}
