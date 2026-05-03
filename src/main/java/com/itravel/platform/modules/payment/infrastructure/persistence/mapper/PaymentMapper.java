package com.itravel.platform.modules.payment.infrastructure.persistence.mapper;

import com.itravel.platform.common.domain.aggregate.valueobject.Money;
import com.itravel.platform.common.domain.enums.CurrencyCode;
import com.itravel.platform.modules.payment.application.dto.PaymentResponse;
import com.itravel.platform.modules.payment.domain.payment.*;
import com.itravel.platform.modules.payment.infrastructure.persistence.entity.PaymentJpaEntity;
import com.itravel.platform.modules.payment.share.PaymentValueObjectMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        uses = {PaymentValueObjectMapper.class},
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface PaymentMapper {

    @Mapping(target = "totalAmount", source = "totalAmount.amount")
    @Mapping(target = "currency", source = "totalAmount.currency")
    PaymentJpaEntity toPaymentJpaEntity(Payment payment);

    static Payment toPaymentDomain(PaymentJpaEntity entity) {
        if (entity == null) return null;
        return Payment.fromExistingBuilder()
                .id(new PaymentId(entity.getId()))
                .referenceCode(new PaymentReferenceCode(entity.getReferenceCode()))
                .totalAmount(Money.of(entity.getTotalAmount(), CurrencyCode.valueOf(entity.getCurrency())))
                .paymentMethod(entity.getPaymentMethod())
                .gatewayTransactionId(entity.getGatewayTransactionId())
                .status(entity.getStatus())
                .failureReason(entity.getFailureReason())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

    static PaymentResponse toPaymentResponse(PaymentJpaEntity entity) {
        if (entity == null) return null;
        return PaymentResponse.builder()
                .id(entity.getId())
                .referenceCode(entity.getReferenceCode())
                .totalAmount(entity.getTotalAmount())
                .currency(entity.getCurrency())
                .paymentMethod(entity.getPaymentMethod().name())
                .status(entity.getStatus())
                .gatewayTransactionId(entity.getGatewayTransactionId())
                .failureReason(entity.getFailureReason())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}
