package com.itravel.platform.modules.payment.application.port.in.payment;

import com.itravel.platform.modules.payment.api.dto.PaymentUrlResponse;
import com.itravel.platform.modules.payment.application.command.model.CreatePaymentCommand;

import java.io.UnsupportedEncodingException;

public interface CreatePaymentUseCase {
    PaymentUrlResponse execute(CreatePaymentCommand command) throws UnsupportedEncodingException;
}
