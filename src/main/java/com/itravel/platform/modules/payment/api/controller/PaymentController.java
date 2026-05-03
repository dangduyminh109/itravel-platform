package com.itravel.platform.modules.payment.api.controller;

import com.itravel.platform.common.dto.ApiResponse;
import com.itravel.platform.modules.payment.api.dto.CreatePaymentRequest;
import com.itravel.platform.modules.payment.api.dto.PaymentUrlResponse;
import com.itravel.platform.modules.payment.api.mapper.PaymentRestMapper;
import com.itravel.platform.modules.payment.application.port.in.payment.facade.PaymentCommandFacade;
import com.itravel.platform.modules.payment.share.RequestUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("/api/payments")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PaymentController {
    PaymentCommandFacade paymentCommandFacade;
    PaymentRestMapper mapper;

    @PostMapping
    public ApiResponse<PaymentUrlResponse> create(@RequestBody CreatePaymentRequest request, HttpServletRequest httpRequest) throws UnsupportedEncodingException {
        String clientId = RequestUtils.getIpAddress(httpRequest);
        return ApiResponse.<PaymentUrlResponse>builder()
                .success(true)
                .message("Create payment successfully")
                .response(paymentCommandFacade.createPayment(mapper.toCommand(request, clientId)))
                .build();
    }
}
