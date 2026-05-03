package com.itravel.platform.modules.payment.application.query.payment;

import com.itravel.platform.modules.payment.application.dto.PaymentResponse;
import com.itravel.platform.modules.payment.application.port.out.payment.PaymentQueryPort;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class GetPaymentHandler {
    PaymentQueryPort paymentQueryPort;

    public Optional<PaymentResponse> execute(String id) {
        return paymentQueryPort.findById(id);
    }

    public Optional<PaymentResponse> executeByReference(String referenceCode) {
        return paymentQueryPort.findByReferenceCode(referenceCode);
    }
}
