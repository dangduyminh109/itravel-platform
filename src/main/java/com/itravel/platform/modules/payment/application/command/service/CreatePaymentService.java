package com.itravel.platform.modules.payment.application.command.service;

import com.itravel.platform.common.domain.aggregate.valueobject.Money;
import com.itravel.platform.modules.booking.application.dto.BookingDetailDTO;
import com.itravel.platform.modules.booking.application.exception.BookingNotFoundException;
import com.itravel.platform.modules.booking.application.port.in.booking.facade.BookingQueryFacade;
import com.itravel.platform.modules.payment.api.dto.PaymentUrlResponse;
import com.itravel.platform.modules.payment.application.command.model.CreatePaymentCommand;
import com.itravel.platform.modules.payment.application.exception.PaymentGatewayNotFoundException;
import com.itravel.platform.modules.payment.application.port.in.payment.CreatePaymentUseCase;
import com.itravel.platform.modules.payment.application.port.out.payment.PaymentGatewayPort;
import com.itravel.platform.modules.payment.domain.payment.Payment;
import com.itravel.platform.modules.payment.domain.payment.PaymentReferenceCode;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CreatePaymentService implements CreatePaymentUseCase {
    Map<String, PaymentGatewayPort> gateways;
    BookingQueryFacade bookingQueryFacade;

    @Override
    @Transactional
    public PaymentUrlResponse execute(CreatePaymentCommand command) throws UnsupportedEncodingException {
        BookingDetailDTO booking = bookingQueryFacade.getBookingDetail(command.bookingCode())
                .orElseThrow(BookingNotFoundException::new);

        Payment payment = Payment.create(
                new PaymentReferenceCode(command.bookingCode()),
                Money.of(booking.totalAmount(), booking.currency()),
                command.paymentMethod());

        PaymentGatewayPort paymentGatewayPort = Optional
                .ofNullable(gateways.get(command.paymentMethod().name() + "_GATEWAY"))
                .orElseThrow(PaymentGatewayNotFoundException::new);

        return paymentGatewayPort.createPaymentUrl(payment, command.clientId());
    }
}
