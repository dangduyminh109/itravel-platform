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
    EMAIL_SEND_FAILED("EMAIL_SEND_FAILED", "Failed to send email",HttpStatus.INTERNAL_SERVER_ERROR,"email");
    ;

    String code;
    String message;
    HttpStatus httpStatusCode;
    String field;
}
