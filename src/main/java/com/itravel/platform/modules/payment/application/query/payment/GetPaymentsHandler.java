package com.itravel.platform.modules.payment.application.query.payment;

import com.itravel.platform.common.dto.PageResponse;
import com.itravel.platform.modules.payment.application.dto.PaymentResponse;
import com.itravel.platform.modules.payment.application.port.out.payment.PaymentQueryPort;
import com.itravel.platform.modules.payment.domain.payment.PaymentStatus;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class GetPaymentsHandler {
    PaymentQueryPort paymentQueryPort;

    public PageResponse<PaymentResponse> execute(String keyword, Pageable pageable, PaymentStatus status) {
        Page<PaymentResponse> paymentPage = paymentQueryPort.getPayments(keyword, pageable, status);

        return PageResponse.<PaymentResponse>builder()
                .currentPage(paymentPage.getNumber())
                .pageSize(paymentPage.getSize())
                .totalElements(paymentPage.getTotalElements())
                .totalPages(paymentPage.getTotalPages())
                .data(paymentPage.getContent())
                .build();
    }
}
