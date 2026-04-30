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
    PHONE_NUMBER_INVALID("PHONE_NUMBER_INVALID", "Phone number is invalid. It must be 10-15 digits and may start with '+'.", HttpStatus.BAD_REQUEST, "phoneNumber"),
    GENDER_INVALID("GENDER_INVALID", "Gender must be MALE, FEMALE or OTHER.", HttpStatus.BAD_REQUEST, "gender"),
    DATE_OF_BIRTH_MUST_BE_PAST_OR_PRESENT("DATE_OF_BIRTH_MUST_BE_PAST_OR_PRESENT", "Date of birth must be in the past or present.", HttpStatus.BAD_REQUEST, "dateOfBirth"),

    // Address validation
    ADDRESS_DETAIL_CANNOT_BE_BLANK("ADDRESS_DETAIL_CANNOT_BE_BLANK", "Address detail cannot be blank.", HttpStatus.BAD_REQUEST, "detail"),
    WARD_ID_CANNOT_BE_NULL("WARD_ID_CANNOT_BE_NULL", "Ward ID cannot be null.", HttpStatus.BAD_REQUEST, "wardId"),
    PROVINCE_ID_CANNOT_BE_NULL("PROVINCE_ID_CANNOT_BE_NULL", "Province ID cannot be null.", HttpStatus.BAD_REQUEST, "provinceId"),

    // IdentityCard validation
    DOCUMENT_NUMBER_CANNOT_BE_BLANK("DOCUMENT_NUMBER_CANNOT_BE_BLANK", "Document number cannot be blank.", HttpStatus.BAD_REQUEST, "documentNumber"),
    ISSUE_DATE_CANNOT_BE_NULL("ISSUE_DATE_CANNOT_BE_NULL", "Issue date cannot be null.", HttpStatus.BAD_REQUEST, "issueDate"),
    ISSUE_DATE_MUST_BE_PAST_OR_PRESENT("ISSUE_DATE_MUST_BE_PAST_OR_PRESENT", "Issue date must be in the past or present.", HttpStatus.BAD_REQUEST, "issueDate"),
    ISSUE_PLACE_CANNOT_BE_BLANK("ISSUE_PLACE_CANNOT_BE_BLANK", "Issue place cannot be blank.", HttpStatus.BAD_REQUEST, "issuePlace"),

    // Passport validation
    EXPIRY_DATE_CANNOT_BE_NULL("EXPIRY_DATE_CANNOT_BE_NULL", "Expiry date cannot be null.", HttpStatus.BAD_REQUEST, "expiryDate"),
    EXPIRY_DATE_MUST_BE_FUTURE("EXPIRY_DATE_MUST_BE_FUTURE", "Expiry date must be in the future.", HttpStatus.BAD_REQUEST, "expiryDate"),
    ISSUE_DATE_MUST_BE_BEFORE_EXPIRY_DATE("ISSUE_DATE_MUST_BE_BEFORE_EXPIRY_DATE", "Issue date must be before expiry date.", HttpStatus.BAD_REQUEST, "issueDate"),

    UPLOAD_FAILED("UPLOAD_FAILED", "Failed to upload file to cloud storage.", HttpStatus.INTERNAL_SERVER_ERROR, "file"),

    // location validation
    TYPE_CANNOT_BE_BLANK("TYPE_CANNOT_BE_BLANK", "Type cannot be left blank.", HttpStatus.BAD_REQUEST, "type"),

    // tour
    TOUR_STATUS_CANNOT_BE_BLANK("TOUR_STATUS_CANNOT_BE_BLANK", "Tour status cannot be left blank.", HttpStatus.BAD_REQUEST, "status"),
    TOUR_NAME_CANNOT_BE_BLANK("TOUR_NAME_CANNOT_BE_BLANK", "Tour name cannot be left blank.", HttpStatus.BAD_REQUEST, "name"),
    TOUR_CATEGORY_ID_CANNOT_BE_NULL("TOUR_CATEGORY_ID_CANNOT_BE_NULL", "Tour category id cannot be null.", HttpStatus.BAD_REQUEST, "categoryId"),
    TOUR_DEPARTURE_LOCATION_ID_CANNOT_BE_NULL("TOUR_DEPARTURE_LOCATION_ID_CANNOT_BE_NULL", "Departure location id cannot be null.", HttpStatus.BAD_REQUEST, "departureLocationId"),
    TOUR_DESTINATION_LOCATION_ID_CANNOT_BE_NULL("TOUR_DESTINATION_LOCATION_ID_CANNOT_BE_NULL", "Destination location id cannot be null.", HttpStatus.BAD_REQUEST, "destinationLocationId"),
    TOUR_ORIGINAL_PRICE_CANNOT_BE_NULL("TOUR_ORIGINAL_PRICE_CANNOT_BE_NULL", "Original price cannot be null.", HttpStatus.BAD_REQUEST, "originalPrice"),
    TOUR_DURATION_DAYS_CANNOT_BE_NULL("TOUR_DURATION_DAYS_CANNOT_BE_NULL", "Duration days cannot be null.", HttpStatus.BAD_REQUEST, "days"),
    TOUR_DURATION_NIGHTS_CANNOT_BE_NULL("TOUR_DURATION_NIGHTS_CANNOT_BE_NULL", "Duration nights cannot be null.", HttpStatus.BAD_REQUEST, "nights"),
    TOUR_ITINERARY_DAY_NUMBER_CANNOT_BE_NULL("TOUR_ITINERARY_DAY_NUMBER_CANNOT_BE_NULL", "Itinerary day number cannot be null.", HttpStatus.BAD_REQUEST, "dayNumber"),
    TOUR_ITINERARY_TITLE_CANNOT_BE_BLANK("TOUR_ITINERARY_TITLE_CANNOT_BE_BLANK", "Itinerary title cannot be left blank.", HttpStatus.BAD_REQUEST, "title"),
    TOUR_ITINERARY_TITLE_TOO_LONG("TOUR_ITINERARY_TITLE_TOO_LONG", "Itinerary title must not exceed 200 characters.", HttpStatus.BAD_REQUEST, "title"),
    TOUR_ITINERARY_ACTIVITIES_CANNOT_BE_NULL("TOUR_ITINERARY_ACTIVITIES_CANNOT_BE_NULL", "Itinerary activities cannot be null.", HttpStatus.BAD_REQUEST, "activities"),
    TOUR_ITINERARY_ACTIVITIES_MIN("TOUR_ITINERARY_ACTIVITIES_MIN", "Itinerary activities must have at least one item.", HttpStatus.BAD_REQUEST, "activities"),
    TOUR_IMAGE_FILE_CANNOT_BE_NULL("TOUR_IMAGE_FILE_CANNOT_BE_NULL", "Tour image file cannot be null.", HttpStatus.BAD_REQUEST, "image"),
    TOUR_IMAGE_THUMBNAIL_CANNOT_BE_NULL("TOUR_IMAGE_THUMBNAIL_CANNOT_BE_NULL", "Tour image thumbnail flag cannot be null.", HttpStatus.BAD_REQUEST, "isThumbnail"),
    TOUR_STATUS_INVALID("TOUR_STATUS_INVALID", "Status must be ACTIVE, INACTIVE or DRAFT.", HttpStatus.BAD_REQUEST, "status"),
    // schedule
    SCHEDULE_STATUS_INVALID("SCHEDULE_STATUS_INVALID", "Status must be OPEN, FULL, CANCELLED or COMPLETED.", HttpStatus.BAD_REQUEST, "status"),
    TOUR_ID_CANNOT_BE_BLANK("TOUR_ID_CANNOT_BE_BLANK", "Tour id cannot be blank", HttpStatus.BAD_REQUEST, null),
    AVAILABLE_SEATS_INVALID("AVAILABLE_SEATS_INVALID", "Available seats must be greater than or equal to 0", HttpStatus.BAD_REQUEST, null),
    DISCOUNT_PRICE_MUST_BE_POSITIVE("DISCOUNT_PRICE_MUST_BE_POSITIVE","Discount price must be greater than 0.",HttpStatus.BAD_REQUEST,"discountPrice")
    ;

    String code;
    String message;
    HttpStatus httpStatusCode;
    String field;
}
