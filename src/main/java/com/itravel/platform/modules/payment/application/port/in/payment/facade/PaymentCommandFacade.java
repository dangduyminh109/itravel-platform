package com.itravel.platform.modules.payment.application.port.in.payment.facade;

import com.itravel.platform.modules.payment.api.dto.PaymentUrlResponse;
import com.itravel.platform.modules.payment.application.command.model.CreatePaymentCommand;
import com.itravel.platform.modules.payment.application.command.model.ProcessIpnCommand;
import com.itravel.platform.modules.payment.application.port.in.payment.CreatePaymentUseCase;
import com.itravel.platform.modules.payment.application.port.in.payment.ProcessPaymentIpnUseCase;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import java.io.UnsupportedEncodingException;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PaymentCommandFacade {
    CreatePaymentUseCase createPaymentUseCase;
    ProcessPaymentIpnUseCase processPaymentIpnUseCase;

    public PaymentUrlResponse createPayment(CreatePaymentCommand command) throws UnsupportedEncodingException {
        return createPaymentUseCase.execute(command);
    }

    public boolean processIpn(ProcessIpnCommand command) {
        return processPaymentIpnUseCase.execute(command);
    }
}
