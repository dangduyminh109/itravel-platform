package com.itravel.platform.modules.payment.application.command.model;

import java.util.Map;

public record ProcessIpnCommand(
        String transactionId,
        String status,
        Map<String, String> params
) {
}
