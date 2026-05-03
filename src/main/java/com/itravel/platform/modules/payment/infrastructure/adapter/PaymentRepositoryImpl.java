package com.itravel.platform.modules.payment.infrastructure.adapter;

import com.itravel.platform.modules.payment.application.port.out.payment.PaymentRepositoryPort;
import com.itravel.platform.modules.payment.domain.payment.Payment;
import com.itravel.platform.modules.payment.domain.payment.PaymentId;
import com.itravel.platform.modules.payment.domain.payment.PaymentReferenceCode;
import com.itravel.platform.modules.payment.infrastructure.persistence.entity.PaymentJpaEntity;
import com.itravel.platform.modules.payment.infrastructure.persistence.mapper.PaymentMapper;
import com.itravel.platform.modules.payment.infrastructure.persistence.repository.PaymentJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PaymentRepositoryImpl implements PaymentRepositoryPort {

    private final PaymentJpaRepository jpaRepository;
    private final PaymentMapper paymentMapper;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    public Payment save(Payment payment) {
        PaymentJpaEntity entity = paymentMapper.toPaymentJpaEntity(payment);
        jpaRepository.save(entity);

        payment.getDomainEvents().forEach(eventPublisher::publishEvent);
        payment.clearDomainEvents();
        
        return payment;
    }

    @Override
    public Optional<Payment> findById(PaymentId id) {
        return jpaRepository.findById(id.value()).map(PaymentMapper::toPaymentDomain);
    }

    @Override
    public Optional<Payment> findByReferenceCode(PaymentReferenceCode referenceCode) {
        return jpaRepository.findByReferenceCode(referenceCode.value()).map(PaymentMapper::toPaymentDomain);
    }
}
