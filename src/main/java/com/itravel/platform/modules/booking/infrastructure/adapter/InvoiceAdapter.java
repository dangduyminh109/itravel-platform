package com.itravel.platform.modules.booking.infrastructure.adapter;

import com.itravel.platform.modules.booking.application.port.out.external.InvoicePort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class InvoiceAdapter implements InvoicePort {

    @Override
    public String generateInvoice(String bookingId) {
        log.info("Mock generating invoice for booking {}", bookingId);
        return "https://mock-invoice-url.com/invoice_" + bookingId + ".pdf";
    }
}
