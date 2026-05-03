package com.itravel.platform.modules.payment.domain.payment;

import com.itravel.platform.common.domain.BaseAggregate;
import com.itravel.platform.common.domain.aggregate.valueobject.Money;
import com.itravel.platform.common.utils.RandomUtils;
import com.itravel.platform.modules.payment.domain.event.PaymentCompletedEvent;
import com.itravel.platform.modules.payment.domain.event.PaymentFailedEvent;
import com.itravel.platform.modules.payment.domain.exception.PaymentInvalidStateTransitionException;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Payment extends BaseAggregate<PaymentId> {
    PaymentReferenceCode referenceCode;
    Money totalAmount;
    PaymentMethod paymentMethod;
    String gatewayTransactionId;
    PaymentStatus status;
    String failureReason;

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("ddMMyyyy").withZone(ZoneId.systemDefault());

    private Payment(PaymentReferenceCode referenceCode, Money totalAmount, PaymentMethod paymentMethod) {
        super(PaymentId.generate());
        this.referenceCode = referenceCode;
        this.totalAmount = totalAmount;
        this.paymentMethod = paymentMethod;
        this.status = PaymentStatus.PENDING;
        this.gatewayTransactionId = genGatewayTransactionId();
    }

    private Payment(
            PaymentId id,
            PaymentReferenceCode referenceCode,
            Money totalAmount,
            PaymentMethod paymentMethod,
            String gatewayTransactionId,
            PaymentStatus status,
            String failureReason,
            Instant createdAt,
            Instant updatedAt
    ) {
        super(id, createdAt, updatedAt);
        this.referenceCode = referenceCode;
        this.totalAmount = totalAmount;
        this.paymentMethod = paymentMethod;
        this.gatewayTransactionId = gatewayTransactionId;
        this.status = status;
        this.failureReason = failureReason;
    }

    public static Payment create(PaymentReferenceCode referenceCode, Money totalAmount, PaymentMethod paymentMethod) {
        return new Payment(referenceCode, totalAmount, paymentMethod);
    }

    @Builder(builderMethodName = "fromExistingBuilder")
    public static Payment fromExisting(
            PaymentId id,
            PaymentReferenceCode referenceCode,
            Money totalAmount,
            PaymentMethod paymentMethod,
            String gatewayTransactionId,
            PaymentStatus status,
            String failureReason,
            Instant createdAt,
            Instant updatedAt
    ) {
        return new Payment(
            id,
            referenceCode,
            totalAmount,
            paymentMethod,
            gatewayTransactionId,
            status,
            failureReason,
            createdAt,
            updatedAt
        );
    }

    private String genGatewayTransactionId() {
        String date = DATE_FORMATTER.format(Instant.now());
        return "PAY" + date + RandomUtils.getRandomNumber(8);
    }

    public void markAsSuccess(String externalTransactionId) {
        if (this.status != PaymentStatus.PENDING) {
            throw new PaymentInvalidStateTransitionException();
        }
        this.status = PaymentStatus.SUCCESS;
        touch();
        registerEvent(new PaymentCompletedEvent(
            getId().value(),
            getReferenceCode().value(),
            getTotalAmount().getAmount(),
            this.gatewayTransactionId,
            getUpdatedAt()
        ));
    }

    public void markAsFailed(String reason) {
        if (this.status != PaymentStatus.PENDING) {
            throw new PaymentInvalidStateTransitionException();
        }
        this.status = PaymentStatus.FAILED;
        this.failureReason = reason;
        touch();
        registerEvent(new PaymentFailedEvent(
            getId().value(),
            getReferenceCode().value(),
            reason,
            getUpdatedAt()
        ));
    }
}
