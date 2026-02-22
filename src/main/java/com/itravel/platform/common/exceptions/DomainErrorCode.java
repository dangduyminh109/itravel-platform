package com.itravel.platform.common.exceptions;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PACKAGE)
public enum DomainErrorCode {

    // ===== AUTH / USER =====
    TOKEN_NOT_OWNED("TOKEN_NOT_OWNED", "Token does not belong to the user", HttpStatus.FORBIDDEN, "token"),
    INVALID_TOKEN("INVALID_TOKEN", "Invalid token", HttpStatus.UNAUTHORIZED, "token"),
    INVALID_PASSWORD_FOR_GOOGLE_ACCOUNT("INVALID_PASSWORD_FOR_GOOGLE_ACCOUNT", "Google account cannot have password", HttpStatus.BAD_REQUEST, "password"),

    INVALID_ROLE_NAME("INVALID_ROLE_NAME", "Invalid role name", HttpStatus.BAD_REQUEST, "roleName"),
    ROLE_NAME_TOO_LONG("ROLE_NAME_TOO_LONG", "Role name is too long", HttpStatus.BAD_REQUEST, "roleName"),
    INVALID_ROLE_ID("INVALID_ROLE_ID", "Invalid role id", HttpStatus.BAD_REQUEST, "roleId"),
    ADMIN_ROLE_IMMUTABLE("ADMIN_ROLE_IMMUTABLE", "Admin role cannot be modified", HttpStatus.FORBIDDEN, null),
    INVALID_PERMISSION_CODE("INVALID_PERMISSION_CODE", "Invalid permission code", HttpStatus.BAD_REQUEST, "permissionCode"),

    INVALID_EMAIL("INVALID_EMAIL", "Invalid email", HttpStatus.BAD_REQUEST, "email"),
    INVALID_PASSWORD("INVALID_PASSWORD", "Password must be at least 8 characters long and contain at least one uppercase letter, one lowercase letter, one digit, and one special character (@$!%*?&)", HttpStatus.BAD_REQUEST, "password"),
    INVALID_USER_NAME("INVALID_USER_NAME", "Invalid username", HttpStatus.BAD_REQUEST, "username"),
    EMAIL_CREDENTIALS_REQUIRED("EMAIL_CREDENTIALS_REQUIRED", "Email account requires email and password", HttpStatus.BAD_REQUEST, "email"),
    GOOGLE_CREDENTIALS_REQUIRED("GOOGLE_CREDENTIALS_REQUIRED", "Google account requires email", HttpStatus.BAD_REQUEST, "email"),
    USERNAME_CREDENTIALS_REQUIRED("USERNAME_CREDENTIALS_REQUIRED", "Username account requires username and password", HttpStatus.BAD_REQUEST, "username"),
    INVALID_FULL_NAME("INVALID_FULL_NAME", "Invalid full name", HttpStatus.BAD_REQUEST, "fullname"),

    INVALID_OTP_CODE("INVALID_OTP_CODE", "Invalid OTP code", HttpStatus.BAD_REQUEST, "otpCode"),
    OTP_EXPIRED("OTP_EXPIRED", "OTP has expired", HttpStatus.BAD_REQUEST, "otpCode"),
    INVALID_CUSTOMER_ROLE("INVALID_CUSTOMER_ROLE", "Invalid role for customer registration", HttpStatus.BAD_REQUEST, "role")
    ;
    String code;
    String message;
    HttpStatus httpStatusCode;
    String feild;
}
