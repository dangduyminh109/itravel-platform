package com.itravel.platform.modules.booking.application.port.out.external;

import java.math.BigDecimal;

public interface PaymentGatewayPort {
    String generatePaymentUrl(String bookingCode, BigDecimal amount);
}
