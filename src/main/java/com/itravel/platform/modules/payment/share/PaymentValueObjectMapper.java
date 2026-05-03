package com.itravel.platform.modules.payment.share;

import com.itravel.platform.modules.payment.domain.payment.PaymentId;
import com.itravel.platform.modules.payment.domain.payment.PaymentMethod;
import com.itravel.platform.modules.payment.domain.payment.PaymentReferenceCode;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface PaymentValueObjectMapper {
    default PaymentId toPaymentId(String id) {
        return id != null ? new PaymentId(id) : null;
    }

    default String fromPaymentId(PaymentId id) {
        return id != null ? id.value() : null;
    }

    default PaymentReferenceCode toPaymentReferenceCode(String code) {
        return code != null ? new PaymentReferenceCode(code) : null;
    }

    default String fromPaymentReferenceCode(PaymentReferenceCode code) {
        return code != null ? code.value() : null;
    }

    default PaymentMethod toPaymentMethod(String method) {
        return method != null ? PaymentMethod.valueOf(method.toUpperCase()) : null;
    }

    default String fromPaymentMethod(PaymentMethod method) {
        return method != null ? method.name() : null;
    }

    default com.itravel.platform.common.domain.enums.CurrencyCode toCurrencyCode(String currency) {
        return currency != null ? com.itravel.platform.common.domain.enums.CurrencyCode.valueOf(currency) : null;
    }

    default String fromCurrencyCode(com.itravel.platform.common.domain.enums.CurrencyCode currency) {
        return currency != null ? currency.name() : null;
    }
}
