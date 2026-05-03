package com.itravel.platform.modules.payment.application.event.handler.payment;

import com.itravel.platform.modules.payment.domain.event.PaymentFailedEvent;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PaymentFailedEventHandler {

    @Async
    @EventListener
    public void handle(PaymentFailedEvent event) {
        log.warn("Handling PaymentFailedEvent for booking code: {}. Reason: {}", 
                event.referenceCode(), event.reason());
    }
}
