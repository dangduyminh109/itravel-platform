package com.itravel.platform.modules.payment.application.port.out.payment;

import com.itravel.platform.modules.payment.application.dto.PaymentResponse;
import com.itravel.platform.modules.payment.domain.payment.PaymentStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface PaymentQueryPort {
    Optional<PaymentResponse> findById(String id);
    Optional<PaymentResponse> findByReferenceCode(String referenceCode);
    Page<PaymentResponse> getPayments(String keyword, Pageable pageable, PaymentStatus status);
}
