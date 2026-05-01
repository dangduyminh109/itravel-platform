package com.itravel.platform.modules.booking.domain.booking;

import com.itravel.platform.modules.booking.domain.bookingItem.ServiceType;
import com.itravel.platform.modules.booking.domain.exception.InvalidBookingCodeException;
import com.itravel.platform.modules.booking.domain.exception.InvalidBookingCodeFormatException;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

public record BookingCode(String value) {

    private static final DateTimeFormatter DATE_FMT = DateTimeFormatter.ofPattern("yyMMdd");
    private static final String ALPHANUMERIC = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int RANDOM_LENGTH = 6;
    private static final SecureRandom RANDOM = new SecureRandom();
    private static final Pattern FORMAT_PATTERN = Pattern.compile("^[A-Z]+-\\d{6}-[A-Z0-9]{6}$");

    public BookingCode {
        if (value == null || value.isBlank()) {
            throw new InvalidBookingCodeException();
        }
        if (!FORMAT_PATTERN.matcher(value).matches()) {
            throw new InvalidBookingCodeFormatException();
        }
    }

    public static BookingCode generate(ServiceType serviceType) {
        String prefix = serviceType.getPrefix();
        String datePart = LocalDate.now().format(DATE_FMT);
        String randomPart = generateRandom();
        return new BookingCode(prefix + "-" + datePart + "-" + randomPart);
    }

    private static String generateRandom() {
        StringBuilder sb = new StringBuilder(RANDOM_LENGTH);
        for (int i = 0; i < RANDOM_LENGTH; i++) {
            sb.append(ALPHANUMERIC.charAt(RANDOM.nextInt(ALPHANUMERIC.length())));
        }
        return sb.toString();
    }
}
