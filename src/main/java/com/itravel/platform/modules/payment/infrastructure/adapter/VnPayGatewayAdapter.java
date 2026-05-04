package com.itravel.platform.modules.payment.infrastructure.adapter;

import com.itravel.platform.modules.payment.api.dto.PaymentUrlResponse;
import com.itravel.platform.modules.payment.application.command.model.ProcessIpnCommand;
import com.itravel.platform.modules.payment.application.port.out.payment.PaymentGatewayPort;
import com.itravel.platform.modules.payment.domain.payment.Payment;
import com.itravel.platform.modules.payment.domain.payment.PaymentStatus;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

@Component("VNPAY_GATEWAY")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class VnPayGatewayAdapter implements PaymentGatewayPort {
    @NonFinal
    @Value("${payment.vnp.tmn-code}")
    String TMN_CODE;
    @NonFinal
    @Value("${payment.vnp.hash-secret}")
    String SECRET_KEY;
    @NonFinal
    @Value("${payment.vnp.return-url}")
    String RETURN_URL;
    @NonFinal
    @Value("${payment.vnp.pay-url}")
    String PAY_URL;

    @Override
    public boolean verifySignature(ProcessIpnCommand command) {
        Map<String, String> fields = new HashMap<>(command.params());
        String vnp_SecureHash = fields.remove("vnp_SecureHash");
        fields.remove("vnp_SecureHashType");

        List<String> fieldNames = new ArrayList<>(fields.keySet());
        Collections.sort(fieldNames);
        
        StringBuilder hashData = new StringBuilder();
        Iterator<String> itr = fieldNames.iterator();
        while (itr.hasNext()) {
            String fieldName = itr.next();
            String fieldValue = fields.get(fieldName);
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                try {
                    hashData.append(fieldName);
                    hashData.append('=');
                    hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.UTF_8.toString()));
                    if (itr.hasNext()) {
                        hashData.append('&');
                    }
                } catch (UnsupportedEncodingException e) {
                    return false;
                }
            }
        }
        String sign = hmacSHA512(SECRET_KEY, hashData.toString());
        return sign.equalsIgnoreCase(vnp_SecureHash);
    }

    @Override
    public PaymentStatus mapStatus(String status) {
        return "00".equals(status)
                ? PaymentStatus.SUCCESS
                : PaymentStatus.FAILED;
    }

    @Override
    public PaymentUrlResponse createPaymentUrl(Payment payment, String clientId) throws UnsupportedEncodingException {
        String vnp_TxnRef = payment.getGatewayTransactionId();
        String vnp_OrderInfo = "Thanh toan hoa don " + payment.getReferenceCode().value();
        String vnp_TmnCode = TMN_CODE;
        String vnp_HashSecret = SECRET_KEY;
        String vnp_ReturnUrl = RETURN_URL;
        String vnp_IpAddr = clientId;

        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        formatter.setTimeZone(TimeZone.getTimeZone("Asia/Ho_Chi_Minh"));
        String vnp_CreateDate = formatter.format(new Date());

        BigDecimal vnpAmount = payment.getTotalAmount().getAmount().multiply(new BigDecimal(100));
        String vnp_Amount = vnpAmount.toBigInteger().toString();

        Map<String, String> vnp_Params = new HashMap<>();
        vnp_Params.put("vnp_Version", "2.1.0");
        vnp_Params.put("vnp_Command", "pay");
        vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
        vnp_Params.put("vnp_Amount", vnp_Amount);
        vnp_Params.put("vnp_CurrCode", "VND");
        vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
        vnp_Params.put("vnp_OrderInfo", vnp_OrderInfo);
        vnp_Params.put("vnp_Locale", "vn");
        vnp_Params.put("vnp_ReturnUrl", vnp_ReturnUrl);
        vnp_Params.put("vnp_IpAddr", vnp_IpAddr);
        vnp_Params.put("vnp_CreateDate", vnp_CreateDate);
        vnp_Params.put("vnp_OrderType", "billpayment");

        List<String> fieldNames = new ArrayList<>(vnp_Params.keySet());
        Collections.sort(fieldNames);
        StringBuilder hashData = new StringBuilder();
        StringBuilder query = new StringBuilder();
        Iterator<String> itr = fieldNames.iterator();
        while (itr.hasNext()) {
            String fieldName = itr.next();
            String fieldValue = vnp_Params.get(fieldName);
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                //Build hash data
                hashData.append(fieldName);
                hashData.append('=');
                hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.UTF_8.toString()));
                //Build query
                query.append(URLEncoder.encode(fieldName, StandardCharsets.UTF_8.toString()));
                query.append('=');
                query.append(URLEncoder.encode(fieldValue, StandardCharsets.UTF_8.toString()));
                if (itr.hasNext()) {
                    query.append('&');
                    hashData.append('&');
                }
            }
        }
        String queryUrl = query.toString();
        String vnp_SecureHash = hmacSHA512(vnp_HashSecret, hashData.toString());
        queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
        String paymentUrl = PAY_URL + "?" + queryUrl;
        return PaymentUrlResponse.builder()
                .paymentUrl(paymentUrl)
                .transactionId(vnp_TxnRef)
                .build();
    }

    private String hmacSHA512(final String key, final String data) {
        try {
            if (key == null || data == null) {
                throw new NullPointerException();
            }
            final Mac hmac512 = Mac.getInstance("HmacSHA512");
            byte[] hmacKeyBytes = key.getBytes();
            final SecretKeySpec SECRET_KEY = new SecretKeySpec(hmacKeyBytes, "HmacSHA512");
            hmac512.init(SECRET_KEY);
            byte[] dataBytes = data.getBytes(StandardCharsets.UTF_8);
            byte[] result = hmac512.doFinal(dataBytes);
            StringBuilder sb = new StringBuilder(2 * result.length);
            for (byte b : result) {
                sb.append(String.format("%02x", b & 0xff));
            }
            return sb.toString();

        } catch (Exception ex) {
            return "";
        }
    }
}
