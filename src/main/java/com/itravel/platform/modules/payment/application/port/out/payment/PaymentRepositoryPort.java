package com.itravel.platform.modules.payment.application.port.out.payment;

import com.itravel.platform.modules.payment.domain.payment.Payment;
import com.itravel.platform.modules.payment.domain.payment.PaymentId;
import com.itravel.platform.modules.payment.domain.payment.PaymentReferenceCode;

import java.util.Optional;

public interface PaymentRepositoryPort {
    Payment save(Payment payment);
    Optional<Payment> findById(PaymentId id);
    Optional<Payment> findByReferenceCode(PaymentReferenceCode referenceCode);
}
