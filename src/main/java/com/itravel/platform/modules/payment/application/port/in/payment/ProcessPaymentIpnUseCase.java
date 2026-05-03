package com.itravel.platform.modules.payment.application.port.in.payment;

import com.itravel.platform.modules.payment.application.command.model.ProcessIpnCommand;

public interface ProcessPaymentIpnUseCase {
    boolean execute(ProcessIpnCommand command);
}
