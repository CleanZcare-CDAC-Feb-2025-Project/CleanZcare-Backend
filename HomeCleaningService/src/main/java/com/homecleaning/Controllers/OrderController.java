package com.homecleaning.Controllers;

import com.homecleaning.DTO.OrderDto;
import com.homecleaning.DTO.PaymentVerificationDto;
import com.homecleaning.Entity.Orders;
import com.homecleaning.Service.OrderService;
import com.homecleaning.Service.RazorpayService;

import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class OrderController {

    private final OrderService orderService;

    private final RazorpayService razorpayService; // Inject Razorpay service

    @PostMapping
    public ResponseEntity<?> placeOrder(@RequestBody OrderDto orderDto) {
        Orders order = orderService.placeOrder(orderDto);

        // Build response with razorpayOrderId, amount, currency for frontend
        Map<String, Object> response = new HashMap<>();
        response.put("orderId", order.getOrderId());
        response.put("razorpayOrderId", order.getRazorpayOrderId());
        response.put("amount", order.getTotalPrice().multiply(BigDecimal.valueOf(100)).intValue()); // in paise
        response.put("currency", "INR");

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @PostMapping("/verify-payment")
    public ResponseEntity<?> verifyPayment(@RequestBody PaymentVerificationDto paymentDto) {
        try {
            orderService.verifyAndUpdatePayment(paymentDto);
            return ResponseEntity.ok("Payment verified successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    @GetMapping
    public ResponseEntity<?> getAllOrder(){
    	return ResponseEntity.status(HttpStatus.OK).body(orderService.getAllOrders());
    }
}

