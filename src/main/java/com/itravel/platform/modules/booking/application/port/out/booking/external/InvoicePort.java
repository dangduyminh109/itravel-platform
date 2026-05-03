package com.itravel.platform.modules.booking.application.port.out.booking.external;

public interface InvoicePort {
    String generateInvoice(String bookingId);
}
