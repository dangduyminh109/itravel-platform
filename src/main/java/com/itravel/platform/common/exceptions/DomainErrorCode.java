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
    INVALID_PROVIDER_ID("INVALID_PROVIDER_ID", "Invalid provider id", HttpStatus.BAD_REQUEST, "roleId"),

    INVALID_OTP_CODE("INVALID_OTP_CODE", "Invalid OTP code", HttpStatus.BAD_REQUEST, "otpCode"),
    OTP_EXPIRED("OTP_EXPIRED", "OTP has expired", HttpStatus.BAD_REQUEST, "otpCode"),
    INVALID_CUSTOMER_ROLE("INVALID_CUSTOMER_ROLE", "Invalid role for customer registration", HttpStatus.BAD_REQUEST, "role"),
    INVALID_PHONE_NUMBER("INVALID_PHONE_NUMBER", "Invalid phone number format", HttpStatus.BAD_REQUEST, "phoneNumber"),
    INVALID_AVATAR("INVALID_AVATAR", "Invalid avatar URL", HttpStatus.BAD_REQUEST, "avatar"),

    // ===== ADDRESS =====
    ADDRESS_DETAIL_CANNOT_BE_BLANK("ADDRESS_DETAIL_CANNOT_BE_BLANK", "Address detail cannot be blank", HttpStatus.BAD_REQUEST, "detail"),
    WARD_ID_CANNOT_BE_NULL("WARD_ID_CANNOT_BE_NULL", "Ward ID cannot be null", HttpStatus.BAD_REQUEST, "wardId"),
    PROVINCE_ID_CANNOT_BE_NULL("PROVINCE_ID_CANNOT_BE_NULL", "Province ID cannot be null", HttpStatus.BAD_REQUEST, "provinceId"),

    // ===== IDENTITY CARD =====
    DOCUMENT_NUMBER_CANNOT_BE_BLANK("DOCUMENT_NUMBER_CANNOT_BE_BLANK", "Document number cannot be blank", HttpStatus.BAD_REQUEST, "documentNumber"),
    ISSUE_DATE_CANNOT_BE_NULL("ISSUE_DATE_CANNOT_BE_NULL", "Issue date cannot be null", HttpStatus.BAD_REQUEST, "issueDate"),
    ISSUE_DATE_MUST_BE_PAST_OR_PRESENT("ISSUE_DATE_MUST_BE_PAST_OR_PRESENT", "Issue date must be in the past or present", HttpStatus.BAD_REQUEST, "issueDate"),
    ISSUE_PLACE_CANNOT_BE_BLANK("ISSUE_PLACE_CANNOT_BE_BLANK", "Issue place cannot be blank", HttpStatus.BAD_REQUEST, "issuePlace"),

    // ===== PASSPORT =====
    EXPIRY_DATE_CANNOT_BE_NULL("EXPIRY_DATE_CANNOT_BE_NULL", "Expiry date cannot be null", HttpStatus.BAD_REQUEST, "expiryDate"),
    EXPIRY_DATE_MUST_BE_FUTURE("EXPIRY_DATE_MUST_BE_FUTURE", "Expiry date must be in the future", HttpStatus.BAD_REQUEST, "expiryDate"),
    ISSUE_DATE_MUST_BE_BEFORE_EXPIRY_DATE("ISSUE_DATE_MUST_BE_BEFORE_EXPIRY_DATE", "Issue date must be before expiry date", HttpStatus.BAD_REQUEST, "issueDate")
    ;
    String code;
    String message;
    HttpStatus httpStatusCode;
    String feild;
}
