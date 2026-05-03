package com.itravel.platform.modules.payment.application.port.in.payment.facade;

import com.itravel.platform.common.dto.PageResponse;
import com.itravel.platform.modules.payment.application.dto.PaymentResponse;
import com.itravel.platform.modules.payment.application.query.payment.GetPaymentHandler;
import com.itravel.platform.modules.payment.application.query.payment.GetPaymentsHandler;
import com.itravel.platform.modules.payment.domain.payment.PaymentStatus;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PaymentQueryFacade {
    GetPaymentHandler getPaymentHandler;
    GetPaymentsHandler getPaymentsHandler;

    public Optional<PaymentResponse> findById(String id) {
        return getPaymentHandler.execute(id);
    }

    public Optional<PaymentResponse> findByReferenceCode(String referenceCode) {
        return getPaymentHandler.executeByReference(referenceCode);
    }

    public PageResponse<PaymentResponse> getPayments(String keyword, Pageable pageable, PaymentStatus status) {
        return getPaymentsHandler.execute(keyword, pageable, status);
    }
}
