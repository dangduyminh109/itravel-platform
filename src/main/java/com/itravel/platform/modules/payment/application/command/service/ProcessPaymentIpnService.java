package com.itravel.platform.modules.payment.application.command.service;

import com.itravel.platform.modules.payment.application.command.model.ProcessIpnCommand;
import com.itravel.platform.modules.payment.application.port.in.payment.ProcessPaymentIpnUseCase;
import com.itravel.platform.modules.payment.application.port.out.payment.PaymentGatewayPort;
import com.itravel.platform.modules.payment.application.port.out.payment.PaymentRepositoryPort;
import com.itravel.platform.modules.payment.application.exception.PaymentNotFoundException;
import com.itravel.platform.modules.payment.domain.exception.InvalidPaymentReferenceCodeException;
import com.itravel.platform.modules.payment.domain.payment.Payment;
import com.itravel.platform.modules.payment.domain.payment.PaymentReferenceCode;
import com.itravel.platform.modules.payment.domain.payment.PaymentStatus;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProcessPaymentIpnService implements ProcessPaymentIpnUseCase {
    PaymentRepositoryPort repositoryPort;
    Map<String, PaymentGatewayPort> gateways;

    @Override
    @Transactional
    public boolean execute(ProcessIpnCommand command) {
        String transactionId = command.transactionId();
        String status = command.status();

        if (transactionId == null) {
            throw new InvalidPaymentReferenceCodeException();
        }

        Payment payment = repositoryPort.findByReferenceCode(new PaymentReferenceCode(transactionId))
                .orElseThrow(PaymentNotFoundException::new);

        if (payment.getStatus() != PaymentStatus.PENDING) {
            return false;
        }
        PaymentGatewayPort paymentGatewayPort = gateways.get(payment.getPaymentMethod().name() + "_GATEWAY");

        if(!paymentGatewayPort.verifySignature(command)){
            payment.markAsFailed("Invalid signature");
            repositoryPort.save(payment);
            return false;
        }

        PaymentStatus paymentStatus = paymentGatewayPort.mapStatus(command.status());
        if (paymentStatus == PaymentStatus.SUCCESS) {
            payment.markAsSuccess("Gateway response code: " + status);
            repositoryPort.save(payment);
            return true;
        } else {
            payment.markAsFailed("Gateway response code: " + status);
            repositoryPort.save(payment);
            return false;
        }
    }
}
