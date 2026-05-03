package com.itravel.platform.modules.payment.infrastructure.adapter;

import com.itravel.platform.modules.payment.application.dto.PaymentResponse;
import com.itravel.platform.modules.payment.application.port.out.payment.PaymentQueryPort;
import com.itravel.platform.modules.payment.domain.payment.PaymentStatus;
import com.itravel.platform.modules.payment.infrastructure.persistence.mapper.PaymentMapper;
import com.itravel.platform.modules.payment.infrastructure.persistence.repository.PaymentJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PaymentQueryAdapter implements PaymentQueryPort {

    private final PaymentJpaRepository repository;

    @Override
    public Optional<PaymentResponse> findById(String id) {
        return repository.findById(id).map(PaymentMapper::toPaymentResponse);
    }

    @Override
    public Optional<PaymentResponse> findByReferenceCode(String referenceCode) {
        return repository.findByReferenceCode(referenceCode).map(PaymentMapper::toPaymentResponse);
    }

    @Override
    public Page<PaymentResponse> getPayments(String keyword, Pageable pageable, PaymentStatus status) {
        return repository.findAll(pageable).map(PaymentMapper::toPaymentResponse);
    }
}
