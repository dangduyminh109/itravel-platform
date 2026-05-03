package com.itravel.platform.modules.payment.infrastructure.persistence.entity;

import com.itravel.platform.common.infrastructure.JpaBaseModel;
import com.itravel.platform.modules.payment.domain.payment.PaymentMethod;
import com.itravel.platform.modules.payment.domain.payment.PaymentStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@SuperBuilder(toBuilder = true)
@Table(name = "payment")
public class PaymentJpaEntity extends JpaBaseModel {
    @Id
    String id;

    @Column(unique = true, nullable = false)
    String referenceCode;

    @Column(nullable = false)
    BigDecimal totalAmount;

    @Column(nullable = false)
    String currency;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    PaymentMethod paymentMethod;

    String gatewayTransactionId;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    PaymentStatus status;

    @Column(columnDefinition = "TEXT")
    String failureReason;
}
