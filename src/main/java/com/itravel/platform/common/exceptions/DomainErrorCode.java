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

    // ===== COMMON =====
    INVALID_SLUG("INVALID_SLUG", "Invalid slug", HttpStatus.BAD_REQUEST, "slug"),

    // ===== COMMON - MONEY =====
    MONEY_AMOUNT_CANNOT_BE_NULL("MONEY_AMOUNT_CANNOT_BE_NULL", "Amount cannot be null", HttpStatus.BAD_REQUEST, "amount"),
    MONEY_CURRENCY_CANNOT_BE_NULL("MONEY_CURRENCY_CANNOT_BE_NULL", "Currency cannot be null", HttpStatus.BAD_REQUEST, "currency"),
    MONEY_CURRENCY_MISMATCH("MONEY_CURRENCY_MISMATCH", "Cannot operate on Money with different currencies", HttpStatus.BAD_REQUEST, "currency"),

    // ===== IDENTITY - AUTH / USER =====
    TOKEN_NOT_OWNED("TOKEN_NOT_OWNED", "Token does not belong to the user", HttpStatus.FORBIDDEN, "token"),
    INVALID_TOKEN("INVALID_TOKEN", "Invalid token", HttpStatus.UNAUTHORIZED, "token"),
    INVALID_PASSWORD_FOR_GOOGLE_ACCOUNT("INVALID_PASSWORD_FOR_GOOGLE_ACCOUNT", "Google account cannot have password", HttpStatus.BAD_REQUEST, "password"),

    INVALID_ROLE_NAME("INVALID_ROLE_NAME", "Invalid role name", HttpStatus.BAD_REQUEST, "roleName"),
    ROLE_NAME_TOO_LONG("ROLE_NAME_TOO_LONG", "Role name is too long", HttpStatus.BAD_REQUEST, "roleName"),
    INVALID_ROLE_ID("INVALID_ROLE_ID", "Invalid role id", HttpStatus.BAD_REQUEST, "roleId"),
    ROLE_IMMUTABLE("ROLE_IMMUTABLE", "Admin and Customer role cannot be modified", HttpStatus.FORBIDDEN, null),
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

    // ===== IDENTITY - ADDRESS =====
    ADDRESS_DETAIL_CANNOT_BE_BLANK("ADDRESS_DETAIL_CANNOT_BE_BLANK", "Address detail cannot be blank", HttpStatus.BAD_REQUEST, "detail"),
    WARD_ID_CANNOT_BE_NULL("WARD_ID_CANNOT_BE_NULL", "Ward ID cannot be null", HttpStatus.BAD_REQUEST, "wardId"),
    PROVINCE_ID_CANNOT_BE_NULL("PROVINCE_ID_CANNOT_BE_NULL", "Province ID cannot be null", HttpStatus.BAD_REQUEST, "provinceId"),

    // ===== IDENTITY - IDENTITY CARD =====
    DOCUMENT_NUMBER_CANNOT_BE_BLANK("DOCUMENT_NUMBER_CANNOT_BE_BLANK", "Document number cannot be blank", HttpStatus.BAD_REQUEST, "documentNumber"),
    ISSUE_DATE_CANNOT_BE_NULL("ISSUE_DATE_CANNOT_BE_NULL", "Issue date cannot be null", HttpStatus.BAD_REQUEST, "issueDate"),
    ISSUE_DATE_MUST_BE_PAST_OR_PRESENT("ISSUE_DATE_MUST_BE_PAST_OR_PRESENT", "Issue date must be in the past or present", HttpStatus.BAD_REQUEST, "issueDate"),
    ISSUE_PLACE_CANNOT_BE_BLANK("ISSUE_PLACE_CANNOT_BE_BLANK", "Issue place cannot be blank", HttpStatus.BAD_REQUEST, "issuePlace"),

    // ===== IDENTITY - PASSPORT =====
    EXPIRY_DATE_CANNOT_BE_NULL("EXPIRY_DATE_CANNOT_BE_NULL", "Expiry date cannot be null", HttpStatus.BAD_REQUEST, "expiryDate"),
    EXPIRY_DATE_MUST_BE_FUTURE("EXPIRY_DATE_MUST_BE_FUTURE", "Expiry date must be in the future", HttpStatus.BAD_REQUEST, "expiryDate"),
    ISSUE_DATE_MUST_BE_BEFORE_EXPIRY_DATE("ISSUE_DATE_MUST_BE_BEFORE_EXPIRY_DATE", "Issue date must be before expiry date", HttpStatus.BAD_REQUEST, "issueDate"),

    // ===== LOCATION =====
    INVALID_LOCATION_ID("INVALID_LOCATION_ID", "Invalid location id", HttpStatus.BAD_REQUEST, "locationId"),
    INVALID_LOCATION_NAME("INVALID_LOCATION_NAME", "Expiry date cannot be null", HttpStatus.BAD_REQUEST, "locationId"),
    LOCATION_NAME_TOO_LONG("LOCATION_NAME_TOO_LONG", "Location name is too long", HttpStatus.BAD_REQUEST, "locationName"),
    INVALID_TYPE_OR_PARENT("INVALID_TYPE_OR_PARENT", "Invalid type or parent", HttpStatus.BAD_REQUEST, "parentId,type"),

    // ===== TOUR  =====
    INVALID_TOUR_NAME("INVALID_TOUR_NAME", "Invalid tour name", HttpStatus.BAD_REQUEST, "tourName"),
    TOUR_NAME_TOO_LONG("TOUR_NAME_TOO_LONG", "Tour name is too long", HttpStatus.BAD_REQUEST, "tourName"),
    INVALID_CURRENCY("INVALID_CURRENCY", "Invalid currency", HttpStatus.BAD_REQUEST, "currency"),
    INVALID_ORIGIN_PRICE("INVALID_ORIGIN_PRICE", "Invalid origin price", HttpStatus.BAD_REQUEST, "originalPrice"),
    INVALID_DISCOUNT_PRICE("INVALID_DISCOUNT_PRICE", "Invalid discount price", HttpStatus.BAD_REQUEST, "discountPrice"),

    INVALID_DAYS("INVALID_DAYS", "Days must be greater than or equal to 1", HttpStatus.BAD_REQUEST, "days"),
    INVALID_NIGHTS("INVALID_NIGHTS", "Nights must not be negative", HttpStatus.BAD_REQUEST, "nights"),
    INVALID_DAYS_NIGHTS_RELATION("INVALID_DAYS_NIGHTS_RELATION", "Days and nights difference must not exceed 1", HttpStatus.BAD_REQUEST, "days"),

    INVALID_MIN_PARTICIPANTS("INVALID_MIN_PARTICIPANTS", "Minimum participants must not be negative", HttpStatus.BAD_REQUEST, "minParticipants"),
    INVALID_PARTICIPANTS_RANGE("INVALID_PARTICIPANTS_RANGE", "Minimum participants must not exceed maximum participants", HttpStatus.BAD_REQUEST, "minParticipants"),
    SERVICES_OVERLAP("SERVICES_OVERLAP", "Services cannot be both included and excluded", HttpStatus.BAD_REQUEST, "services"),
    TOUR_NAME_CANNOT_BE_BLANK("TOUR_NAME_CANNOT_BE_BLANK", "Tour name cannot be blank", HttpStatus.BAD_REQUEST, "name"),
    TOUR_STATUS_CANNOT_BE_BLANK("TOUR_STATUS_CANNOT_BE_BLANK", "Tour status cannot be blank", HttpStatus.BAD_REQUEST, "status"),
    TOUR_STATUS_INVALID("TOUR_STATUS_INVALID", "Tour status is invalid", HttpStatus.BAD_REQUEST, "status"),
    TOUR_CATEGORY_ID_CANNOT_BE_NULL("TOUR_CATEGORY_ID_CANNOT_BE_NULL", "Category ID cannot be null", HttpStatus.BAD_REQUEST, "categoryId"),
    TOUR_DEPARTURE_LOCATION_ID_CANNOT_BE_NULL("TOUR_DEPARTURE_LOCATION_ID_CANNOT_BE_NULL", "Departure location ID cannot be null", HttpStatus.BAD_REQUEST, "departureLocationId"),
    TOUR_DESTINATION_LOCATION_ID_CANNOT_BE_NULL("TOUR_DESTINATION_LOCATION_ID_CANNOT_BE_NULL", "Destination location ID cannot be null", HttpStatus.BAD_REQUEST, "destinationLocationId"),
    // ===== TOUR - CATEGORY =====
    INVALID_CATEGORY_ID("INVALID_CATEGORY_ID", "Invalid category id", HttpStatus.BAD_REQUEST, "categoryId"),
    INVALID_CATEGORY_NAME("INVALID_CATEGORY_NAME", "Invalid category name", HttpStatus.BAD_REQUEST, "categoryName"),
    CATEGORY_NAME_TOO_LONG("CATEGORY_NAME_TOO_LONG", "Category name is too long", HttpStatus.BAD_REQUEST, "categoryName"),
    NAME_CANNOT_BE_BLANK("NAME_CANNOT_BE_BLANK", "Category name cannot be blank", HttpStatus.BAD_REQUEST, "name"),
    STATUS_CANNOT_BE_BLANK("STATUS_CANNOT_BE_BLANK", "Status cannot be blank", HttpStatus.BAD_REQUEST, "status"),
    STATUS_INVALID("STATUS_INVALID", "Status is invalid", HttpStatus.BAD_REQUEST, "status"),

    // ===== TOUR - ITINERARY =====
    INVALID_ITINERARY_ID("INVALID_ITINERARY_ID", "Invalid itinerary id", HttpStatus.BAD_REQUEST, "itineraryId"),
    INVALID_ITINERARY_DAY_NUMBER("INVALID_ITINERARY_DAY_NUMBER", "Invalid itinerary day number", HttpStatus.BAD_REQUEST, "dayNumber"),
    INVALID_ITINERARY_TITLE("INVALID_ITINERARY_TITLE", "Invalid itinerary title", HttpStatus.BAD_REQUEST, "title"),
    ITINERARY_TITLE_TOO_LONG("ITINERARY_TITLE_TOO_LONG", "Itinerary title is too long", HttpStatus.BAD_REQUEST, "title"),
    INVALID_ITINERARY_ACTIVITIES("INVALID_ITINERARY_ACTIVITIES", "Invalid itinerary activities", HttpStatus.BAD_REQUEST, "activities"),
    ITINERARY_NOT_FOUND("ITINERARY_NOT_FOUND", "Invalid not found", HttpStatus.BAD_REQUEST, "itinerary id"),
    DUPLICATE_ITINERARY_DAY("DUPLICATE_ITINERARY_DAY","Duplicate day numbers in itineraries", HttpStatus.BAD_REQUEST, "itineraries"),
    TOUR_ITINERARY_DAY_NUMBER_CANNOT_BE_NULL("TOUR_ITINERARY_DAY_NUMBER_CANNOT_BE_NULL", "Day number cannot be null", HttpStatus.BAD_REQUEST, "dayNumber"),
    TOUR_ITINERARY_TITLE_CANNOT_BE_BLANK("TOUR_ITINERARY_TITLE_CANNOT_BE_BLANK", "Itinerary title cannot be blank", HttpStatus.BAD_REQUEST, "title"),
    TOUR_ITINERARY_TITLE_TOO_LONG("TOUR_ITINERARY_TITLE_TOO_LONG", "Itinerary title is too long", HttpStatus.BAD_REQUEST, "title"),
    TOUR_ITINERARY_ACTIVITIES_CANNOT_BE_NULL("TOUR_ITINERARY_ACTIVITIES_CANNOT_BE_NULL", "Itinerary activities cannot be null", HttpStatus.BAD_REQUEST, "activities"),
    TOUR_ITINERARY_ACTIVITIES_MIN("TOUR_ITINERARY_ACTIVITIES_MIN", "Must have at least one activity", HttpStatus.BAD_REQUEST, "activities"),
    // ===== TOUR - SCHEDULE =====
    INVALID_SCHEDULE_ID("INVALID_SCHEDULE_ID", "Invalid schedule id", HttpStatus.BAD_REQUEST, "scheduleId"),
    INVALID_DEPARTURE_DATE("INVALID_DEPARTURE_DATE", "Invalid departure date", HttpStatus.BAD_REQUEST, "departureDate"),
    INVALID_AVAILABLE_SEATS("INVALID_AVAILABLE_SEATS", "Invalid available seats", HttpStatus.BAD_REQUEST, "availableSeats"),
    INVALID_SCHEDULE_STATUS("INVALID_SCHEDULE_STATUS", "Invalid schedule status", HttpStatus.BAD_REQUEST, "status"),
    DEPARTURE_DATE_CANNOT_BE_NULL("DEPARTURE_DATE_CANNOT_BE_NULL", "Departure date cannot be null", HttpStatus.BAD_REQUEST, "departureDate"),
    AVAILABLE_SEATS_CANNOT_BE_NULL("AVAILABLE_SEATS_CANNOT_BE_NULLE", "Available seats date cannot be null", HttpStatus.BAD_REQUEST, "availableSeats"),
    SURCHARGE_CANNOT_BE_NULL("SURCHARGE_CANNOT_BE_NULL", "Surcharge cannot be null", HttpStatus.BAD_REQUEST, "surcharge"),
    STATUS_CANNOT_BE_NULL("STATUS_CANNOT_BE_NULL", "Status cannot be null", HttpStatus.BAD_REQUEST, "status"),
    SURCHARGE_MUST_BE_NON_NEGATIVE("SURCHARGE_MUST_BE_NON_NEGATIVE", "Surcharge must be greater than or equal to 0", HttpStatus.BAD_REQUEST, "surcharge"),
    AVAILABLE_SEATS_INVALID("AVAILABLE_SEATS_INVALID", "Available seats must be greater than or equal to 0", HttpStatus.BAD_REQUEST, null),
    SURCHARGE_INVALID("SURCHARGE_INVALID", "Surcharge must be greater than or equal to 0", HttpStatus.BAD_REQUEST, null),
    SCHEDULE_STATUS_CANNOT_BE_BLANK("SCHEDULE_STATUS_CANNOT_BE_BLANK", "Schedule status cannot be blank", HttpStatus.BAD_REQUEST, null),
    SCHEDULE_STATUS_INVALID("SCHEDULE_STATUS_INVALID", "Schedule status must be one of OPEN, FULL, CANCELLED, COMPLETED", HttpStatus.BAD_REQUEST, null),
    TOTAL_SEATS_LOWER_THAN_MIN_PARTICIPANTS("TOTAL_SEATS_LOWER_THAN_MIN_PARTICIPANTS","Total seats is lower than minimum participants",HttpStatus.BAD_REQUEST,"totalSeats"),
    MISSING_ADULT_PRICE("MISSING_ADULT_PRICE", "Adult price is missing", HttpStatus.BAD_REQUEST, "adultPrice"),
    NEGATIVE_SINGLE_SUPPLEMENT("NEGATIVE_SINGLE_SUPPLEMENT", "Single supplement cannot be negative",HttpStatus.BAD_REQUEST, "singleSupplement"),
    ORIGINAL_PRICE_MUST_BE_POSITIVE("ORIGINAL_PRICE_MUST_BE_POSITIVE", "Original price must be positive", HttpStatus.BAD_REQUEST, "originalPrice"),
    DISCOUNT_PRICE_MUST_BE_POSITIVE("DISCOUNT_PRICE_MUST_BE_POSITIVE", "Discount price must be positive", HttpStatus.BAD_REQUEST, "discountPrice"),
    DEPARTURE_DATE_MUST_BE_FUTURE("DEPARTURE_DATE_MUST_BE_FUTURE", "Departure date must be in the future", HttpStatus.BAD_REQUEST, "departureDate"),
    TOTAL_SEATS_CANNOT_BE_NULL("TOTAL_SEATS_CANNOT_BE_NULL", "Total seats cannot be null", HttpStatus.BAD_REQUEST, "totalSeats"),
    TOTAL_SEATS_INVALID("TOTAL_SEATS_INVALID", "Total seats must be non-negative", HttpStatus.BAD_REQUEST, "totalSeats"),
    TOUR_ID_CANNOT_BE_BLANK("TOUR_ID_CANNOT_BE_BLANK", "Tour ID cannot be blank", HttpStatus.BAD_REQUEST, "tourId"),
    // ===== TOUR - IMAGE =====
    INVALID_TOUR_IMAGE_ID("INVALID_TOUR_IMAGE_ID", "Invalid tour image id", HttpStatus.BAD_REQUEST, "tourImageId"),
    INVALID_TOUR_IMAGE_URL("INVALID_TOUR_IMAGE_URL", "Invalid tour image url", HttpStatus.BAD_REQUEST, "imageUrl"),

    // ===== BOOKING =====
    INVALID_BOOKING_ID("INVALID_BOOKING_ID", "Invalid booking id", HttpStatus.BAD_REQUEST, "bookingId"),
    INVALID_BOOKING_CODE("INVALID_BOOKING_CODE", "Invalid booking code", HttpStatus.BAD_REQUEST, "bookingCode"),
    INVALID_BOOKING_CODE_FORMAT("INVALID_BOOKING_CODE_FORMAT", "Booking code format is invalid", HttpStatus.BAD_REQUEST, "bookingCode"),
    BOOKING_CONTACT_FULLNAME_REQUIRED("BOOKING_CONTACT_FULLNAME_REQUIRED", "Contact full name is required", HttpStatus.BAD_REQUEST, "fullName"),
    BOOKING_CONTACT_EMAIL_INVALID("BOOKING_CONTACT_EMAIL_INVALID", "Contact email is invalid", HttpStatus.BAD_REQUEST, "email"),
    BOOKING_CONTACT_PHONE_INVALID("BOOKING_CONTACT_PHONE_INVALID", "Contact phone is invalid", HttpStatus.BAD_REQUEST, "phone"),
    BOOKING_INVALID_STATE_TRANSITION("BOOKING_INVALID_STATE_TRANSITION", "Invalid booking state transition", HttpStatus.CONFLICT, "status"),
    BOOKING_CANNOT_CANCEL_COMPLETED("BOOKING_CANNOT_CANCEL_COMPLETED", "Cannot cancel a completed or already cancelled booking", HttpStatus.CONFLICT, "status"),
    BOOKING_EXPIRED("BOOKING_EXPIRED", "Booking has expired", HttpStatus.CONFLICT, "status"),
    INVALID_PRICE_LINE_NAME("INVALID_PRICE_LINE_NAME", "Price line name cannot be blank", HttpStatus.BAD_REQUEST, "name"),
    INVALID_UNIT_PRICE("INVALID_UNIT_PRICE", "Unit price must be non-negative", HttpStatus.BAD_REQUEST, "unitPrice"),
    INVALID_PRICE_LINE_QUANTITY("INVALID_PRICE_LINE_QUANTITY", "Quantity must be greater than 0", HttpStatus.BAD_REQUEST, "quantity"),
    INVALID_PASSENGER_NAME("INVALID_PASSENGER_NAME", "Passenger name cannot be blank", HttpStatus.BAD_REQUEST, "fullName"),
    INVALID_DATE_OF_BIRTH("INVALID_DATE_OF_BIRTH", "Date of birth is invalid", HttpStatus.BAD_REQUEST, "dateOfBirth"),
    GENDER_INVALID("GENDER_INVALID", "Gender must be MALE, FEMALE or OTHER", HttpStatus.BAD_REQUEST, "gender"),
    INVALID_BOOKING_ITEM_REFERENCE("INVALID_BOOKING_ITEM_REFERENCE", "Booking item reference ID cannot be blank", HttpStatus.BAD_REQUEST, "referenceId"),
    INVALID_SERVICE_TYPE("INVALID_SERVICE_TYPE", "Service type is invalid", HttpStatus.BAD_REQUEST, "serviceType"),
    SNAPSHOT_DATA_REQUIRED("SNAPSHOT_DATA_REQUIRED", "Snapshot data is required for booking item", HttpStatus.BAD_REQUEST, "snapshotData"),
    PRICE_BREAKDOWN_REQUIRED("PRICE_BREAKDOWN_REQUIRED", "Price breakdown cannot be empty", HttpStatus.BAD_REQUEST, "priceBreakdown"),
    BOOKING_CANNOT_MODIFY("BOOKING_CANNOT_MODIFY", "Cannot modify booking in current status", HttpStatus.CONFLICT, "status"),

    // ===== BOOKING - APPLICATION =====
    BOOKING_NOT_FOUND("BOOKING_NOT_FOUND", "Booking not found", HttpStatus.NOT_FOUND, "bookingId"),
    INVALID_PASSENGER_AGE("INVALID_PASSENGER_AGE", "Passenger age does not match the ticket type", HttpStatus.BAD_REQUEST, "dateOfBirth"),
    INVENTORY_UNAVAILABLE("INVENTORY_UNAVAILABLE", "Not enough inventory available for the requested service", HttpStatus.CONFLICT, "quantity"),

    // ===== PAYMENT =====
    INVALID_PAYMENT_ID("INVALID_PAYMENT_ID", "Invalid payment id", HttpStatus.BAD_REQUEST, "paymentId"),
    INVALID_PAYMENT_REFERENCE_CODE("INVALID_PAYMENT_REFERENCE_CODE", "Invalid payment reference code", HttpStatus.BAD_REQUEST, "referenceCode"),
    INVALID_PAYMENT_STATUS("INVALID_PAYMENT_STATUS", "Invalid payment status", HttpStatus.BAD_REQUEST, "status"),
    PAYMENT_INVALID_STATE_TRANSITION("PAYMENT_INVALID_STATE_TRANSITION", "Invalid payment state transition", HttpStatus.CONFLICT, "status"),
    PAYMENT_GATEWAY_NOT_FOUND("PAYMENT_GATEWAY_NOT_FOUND", "Payment gateway not found", HttpStatus.NOT_FOUND, "paymentMethod"),
    PAYMENT_NOT_FOUND("PAYMENT_NOT_FOUND", "Payment not found", HttpStatus.NOT_FOUND, "paymentId"),
;
    String code;
    String message;
    HttpStatus httpStatusCode;
    String feild;
}
