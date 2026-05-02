package com.itravel.platform.common.domain.aggregate.valueobject;

import com.itravel.platform.common.domain.enums.CurrencyCode;
import com.itravel.platform.common.domain.aggregate.valueobject.exception.MoneyAmountCannotBeNullException;
import com.itravel.platform.common.domain.aggregate.valueobject.exception.MoneyCurrencyCannotBeNullException;
import com.itravel.platform.common.domain.aggregate.valueobject.exception.MoneyCurrencyMismatchException;
import lombok.*;
import lombok.experimental.FieldDefaults;
import java.math.BigDecimal;

@Getter
@EqualsAndHashCode
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class Money {
    BigDecimal amount;
    CurrencyCode currency;

    public Money(BigDecimal amount, CurrencyCode currency) {
        if (amount == null) {
            throw new MoneyAmountCannotBeNullException();
        }
        if (currency == null) {
            throw new MoneyCurrencyCannotBeNullException();
        }
        this.amount = amount;
        this.currency = currency;
    }

    public static Money of(BigDecimal amount, CurrencyCode currency) {
        return new Money(amount, currency);
    }

    public static Money zero(CurrencyCode currency) {
        return new Money(BigDecimal.ZERO, currency);
    }

    public Money add(Money other) {
        if (!this.currency.equals(other.currency)) {
            throw new MoneyCurrencyMismatchException();
        }
        return new Money(this.amount.add(other.amount), this.currency);
    }

    public Money multiply(int multiplier) {
        return new Money(this.amount.multiply(BigDecimal.valueOf(multiplier)), this.currency);
    }
}
