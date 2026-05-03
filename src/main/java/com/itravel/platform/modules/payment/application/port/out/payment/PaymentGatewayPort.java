package com.itravel.platform.modules.payment.application.port.out.payment;

import com.itravel.platform.modules.payment.api.dto.PaymentUrlResponse;
import com.itravel.platform.modules.payment.application.command.model.ProcessIpnCommand;
import com.itravel.platform.modules.payment.domain.payment.Payment;
import com.itravel.platform.modules.payment.domain.payment.PaymentStatus;

import java.io.UnsupportedEncodingException;

public interface PaymentGatewayPort {
    boolean verifySignature(ProcessIpnCommand command);
    PaymentStatus mapStatus(String status);
    PaymentUrlResponse createPaymentUrl(Payment payment, String clientId) throws UnsupportedEncodingException;
}
