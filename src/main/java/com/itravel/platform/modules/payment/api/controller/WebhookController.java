package com.itravel.platform.modules.payment.api.controller;

import com.itravel.platform.common.dto.ApiResponse;
import com.itravel.platform.modules.payment.application.command.model.ProcessIpnCommand;
import com.itravel.platform.modules.payment.application.port.in.payment.facade.PaymentCommandFacade;
import com.itravel.platform.modules.payment.share.RequestUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/payments/webhook")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class WebhookController {
    PaymentCommandFacade paymentCommandFacade;

    @NonFinal
    @Value("${frontend.url}")
    private String frontendUrl;

    @PostMapping("/momo")
    public ApiResponse<Void> momo() {
        return ApiResponse.<Void>builder()
                .success(true)
                .message("Process MOMO IPN successfully")
                .build();
    }

    @GetMapping("/vnpay_return")
    public void vnPay(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, String> params = RequestUtils.getFieldsFromRequest(request);
        boolean isSuccess = paymentCommandFacade.processIpn(new ProcessIpnCommand(
                params.get("vnp_TxnRef"),
                params.get("vnp_ResponseCode"),
                params
        ));
        if (isSuccess)
            response.sendRedirect(frontendUrl + "/success");
        else
            response.sendRedirect(frontendUrl + "/error");
    }
}
