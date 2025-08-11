package com.homecleaning.Service;

import com.homecleaning.RazorpayConfig;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.Utils;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class RazorpayService {

    private final RazorpayConfig razorpayConfig;

    public Order createRazorpayOrder(BigDecimal totalPrice, String currency, String receipt) throws Exception {
        RazorpayClient client = new RazorpayClient(razorpayConfig.getKeyId(), razorpayConfig.getKeySecret());

        JSONObject orderRequest = new JSONObject();
        orderRequest.put("amount", totalPrice.multiply(BigDecimal.valueOf(100)).intValue()); // amount in paise
        orderRequest.put("currency", currency);
        orderRequest.put("receipt", receipt);
        orderRequest.put("payment_capture", 1);

        return client.orders.create(orderRequest);
    }

    public boolean verifyPaymentSignature(String orderId, String paymentId, String signature) throws Exception {
        JSONObject params = new JSONObject();
        params.put("razorpay_order_id", orderId);
        params.put("razorpay_payment_id", paymentId);
        params.put("razorpay_signature", signature);

        return Utils.verifyPaymentSignature(params, razorpayConfig.getKeySecret());
    }
}
