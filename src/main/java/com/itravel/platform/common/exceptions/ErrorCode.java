package com.itravel.platform.common.exceptions;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PACKAGE)
public enum ErrorCode {
    // ===== COMMON=====
    INTERNAL_SERVER_ERROR("INTERNAL_SERVER_ERROR", "Internal server error", HttpStatus.INTERNAL_SERVER_ERROR, null),
    INVALID_KEY("INVALID_KEY", "Invalid key", HttpStatus.BAD_REQUEST, "key"),
    INVALID_FILE("INVALID_FILE", "Invalid file", HttpStatus.BAD_REQUEST, "file"),
    UNSUPPORTED_MEDIA_TYPE("UNSUPPORTED_MEDIA_TYPE", "Unsupported media type", HttpStatus.UNSUPPORTED_MEDIA_TYPE, "media-type"),
    INVALID_FORM_FORMAT("INVALID_FORM_FORMAT", "Invalid form format", HttpStatus.BAD_REQUEST, null),
    INVALID_TYPE_DATA("INVALID_TYPE_DATA", "Invalid data type", HttpStatus.BAD_REQUEST, null),

    // ===== AUTH / USER =====
    UNAUTHENTICATED("UNAUTHENTICATED", "Authentication required", HttpStatus.UNAUTHORIZED, null),
    UNAUTHORIZED("UNAUTHORIZED", "Access denied", HttpStatus.FORBIDDEN, null),
    CANNOT_CREATE_TOKEN("CANNOT_CREATE_TOKEN", "Failed to create token", HttpStatus.INTERNAL_SERVER_ERROR, "token"),
    INVALID_TOKEN("INVALID_TOKEN", "Invalid or expired token", HttpStatus.UNAUTHORIZED, "token"),
    EMAIL_SEND_FAILED("EMAIL_SEND_FAILED", "Failed to send email",HttpStatus.INTERNAL_SERVER_ERROR,"email"),

    PERMISSION_CANNOT_BE_BLANK("PERMISSION_CANNOT_BE_BLANK", "Permission name cannot be left blank.", HttpStatus.BAD_REQUEST, "permission"),
    PERMISSION_TYPE_CANNOT_BE_BLANK("PERMISSION_TYPE_CANNOT_BE_BLANK", "Permission type cannot be left blank.", HttpStatus.BAD_REQUEST, "permissionType"),
    PERMISSION_TYPE_INVALID("PERMISSION_TYPE_INVALID", "Permission type must be GRANT or DENY.", HttpStatus.BAD_REQUEST, "permissionType"),
    STATUS_CANNOT_BE_BLANK("STATUS_CANNOT_BE_BLANK", "Status cannot be left blank.", HttpStatus.BAD_REQUEST, "status"),
    STATUS_INVALID("STATUS_INVALID", "Status must be ACTIVE or INACTIVE.", HttpStatus.BAD_REQUEST, "status"),
    USERNAME_CANNOT_BE_BLANK("USERNAME_CANNOT_BE_BLANK", "Username cannot be left blank.", HttpStatus.BAD_REQUEST, "username"),
    FULL_NAME_CANNOT_BE_BLANK("FULL_NAME_CANNOT_BE_BLANK", "Full name cannot be left blank.", HttpStatus.BAD_REQUEST, "fullName"),
    PASSWORD_CANNOT_BE_BLANK("PASSWORD_CANNOT_BE_BLANK", "Password cannot be left blank.", HttpStatus.BAD_REQUEST, "password"),
    PASSWORD_INVALID("PASSWORD_INVALID", "Password must be at least 8 characters long and contain at least one uppercase letter, one lowercase letter, one digit, and one special character (@$!%*?&).", HttpStatus.BAD_REQUEST, "password"),
    NEW_PASSWORD_CANNOT_BE_BLANK("NEW_PASSWORD_CANNOT_BE_BLANK", "New password cannot be left blank.", HttpStatus.BAD_REQUEST, "newPassword"),
    NEW_PASSWORD_INVALID("NEW_PASSWORD_INVALID", "New password must be at least 8 characters long and contain at least one uppercase letter, one lowercase letter, one digit, and one special character (@$!%*?&).", HttpStatus.BAD_REQUEST, "newPassword"),
    CONFIRM_PASSWORD_CANNOT_BE_BLANK("CONFIRM_PASSWORD_CANNOT_BE_BLANK", "Confirm password cannot be left blank.", HttpStatus.BAD_REQUEST, "confirmPassword"),
    EMAIL_CANNOT_BE_BLANK("EMAIL_CANNOT_BE_BLANK", "Email cannot be left blank.", HttpStatus.BAD_REQUEST, "email"),
    EMAIL_INVALID("EMAIL_INVALID", "Email is invalid.", HttpStatus.BAD_REQUEST, "email"),
    OTP_CANNOT_BE_BLANK("OTP_CANNOT_BE_BLANK", "Otp cannot be left blank.", HttpStatus.BAD_REQUEST, "otp"),
    OTP_INVALID("OTP_INVALID", "Otp must be a 6-digit number.", HttpStatus.BAD_REQUEST, "otp"),
    IDENTIFIER_CANNOT_BE_BLANK("IDENTIFIER_CANNOT_BE_BLANK", "Identifier cannot be left blank.", HttpStatus.BAD_REQUEST, "identifier"),
    ACCOUNT_ID_CANNOT_BE_BLANK("ACCOUNT_ID_CANNOT_BE_BLANK", "Account id cannot be left blank.", HttpStatus.BAD_REQUEST, "accountId"),
    REFRESH_TOKEN_CANNOT_BE_BLANK("REFRESH_TOKEN_CANNOT_BE_BLANK", "Refresh token cannot be left blank.", HttpStatus.BAD_REQUEST, "refreshToken"),
    ROLE_LIST_CANNOT_BE_EMPTY("ROLE_LIST_CANNOT_BE_EMPTY", "Role list cannot be empty.", HttpStatus.BAD_REQUEST, "roleList"),
    ROLE_ID_CANNOT_BE_NULL("ROLE_ID_CANNOT_BE_NULL", "Role id cannot be null.", HttpStatus.BAD_REQUEST, "roleList"),
    PERMISSION_CODE_LIST_CANNOT_BE_EMPTY("PERMISSION_CODE_LIST_CANNOT_BE_EMPTY", "Permission code list cannot be empty.", HttpStatus.BAD_REQUEST, "permissionCodeList"),
    NAME_CANNOT_BE_BLANK("NAME_CANNOT_BE_BLANK", "Role name cannot be left blank.",HttpStatus.BAD_REQUEST,"name"),

    FIREBASE_INITIALIZATION_FAILED("FIREBASE_INITIALIZATION_FAILED", "Failed to initialize Firebase. Configuration file not found.", HttpStatus.INTERNAL_SERVER_ERROR, null),
    FIREBASE_UPLOAD_FAILED("FIREBASE_UPLOAD_FAILED", "Failed to upload file to Firebase storage.", HttpStatus.INTERNAL_SERVER_ERROR, "file")

    ;

    String code;
    String message;
    HttpStatus httpStatusCode;
    String field;
}
