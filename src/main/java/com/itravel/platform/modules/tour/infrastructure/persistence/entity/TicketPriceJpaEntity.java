package com.itravel.platform.modules.tour.infrastructure.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.math.BigDecimal;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TicketPriceJpaEntity {
    @Column(precision = 19, scale = 2)
    BigDecimal originalPrice;

    @Column(precision = 19, scale = 2)
    BigDecimal discountPrice;
}
