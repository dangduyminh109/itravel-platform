package com.itravel.platform.modules.booking.application.port.out.external;

public interface InvoicePort {
    String generateInvoice(String bookingId);
}
