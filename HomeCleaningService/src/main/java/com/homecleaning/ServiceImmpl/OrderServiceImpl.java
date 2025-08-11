package com.homecleaning.ServiceImmpl;

import com.homecleaning.DTO.OrderDto;
import com.homecleaning.DTO.OrderLineDto;
import com.homecleaning.DTO.PaymentVerificationDto;
import com.homecleaning.Entity.*;
import com.homecleaning.Repository.CouponRepository;
import com.homecleaning.Repository.CustomerRepository;
import com.homecleaning.Repository.OrderRepository;
import com.homecleaning.Service.OrderService;
import com.homecleaning.Service.RazorpayService;
import com.razorpay.Order;
import com.razorpay.RazorpayException;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final CouponRepository couponRepository;
    private final RazorpayService razorpayService;

    @Override
    public Orders placeOrder(OrderDto dto) {
        Orders order = new Orders();

        // Set Customer
        Customer customer = customerRepository.findById(dto.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        order.setCustomer(customer);

        // Optional Coupon
        if (dto.getCoupanCode() != null && !dto.getCoupanCode().isEmpty()) {
            couponRepository.findByCode(dto.getCoupanCode())
                    .ifPresent(order::setCoupon);
        }

        order.setOrderDate(dto.getOrderDate());
        order.setOrderTime(dto.getOrderTime());
        order.setAddress(dto.getAddress());
        order.setPhoneNo(dto.getPhoneNo());
        order.setTaxRate(dto.getTaxRate().doubleValue());
        order.setTip(dto.getTip());
        order.setAvoidColling(dto.isAvoidColling());
        order.setPaymentMode("COD"); // Example, adjust as needed
        order.setTotalPrice(dto.getTotalPrice());
        order.setStatus(OrderStatus.CREATED);

        // Set OrderLine
        List<OrderLine> orderLines = dto.getOrderline().stream().map(lineDto -> {
            OrderLine line = new OrderLine();
            line.setTitle(lineDto.getTitle());
            line.setQuantity(lineDto.getQuantity());
            line.setPrice(lineDto.getPrice());
            line.setOrders(order); // set reverse reference
            return line;
        }).collect(Collectors.toList());

        order.setOrderline(orderLines);

        Orders savedOrder= orderRepository.save(order);
        try {
            // Create Razorpay order
            Order razorpayOrder = razorpayService.createRazorpayOrder(
                    savedOrder.getTotalPrice(),
                    "INR",
                    "receipt_" + savedOrder.getOrderId());

            savedOrder.setRazorpayOrderId(razorpayOrder.get("id"));
            orderRepository.save(savedOrder);
        } catch (RazorpayException e) {
            throw new RuntimeException("Razorpay order creation failed: " + e.getMessage());
        } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        return savedOrder;
    }
    @Override
    public void verifyAndUpdatePayment(PaymentVerificationDto paymentDto) throws Exception {
        Orders order = orderRepository.findByRazorpayOrderId(paymentDto.getRazorpayOrderId())
                .orElseThrow(() -> new RuntimeException("Order not found"));

        boolean isValid = razorpayService.verifyPaymentSignature(
                paymentDto.getRazorpayOrderId(),
                paymentDto.getRazorpayPaymentId(),
                paymentDto.getRazorpaySignature()
        );

        if (!isValid) {
            throw new RuntimeException("Invalid payment signature");
        }

        order.setPaymentStatus(PaymentStatus.SUCCESS); // or appropriate enum
        order.setStatus(OrderStatus.CREATED);
        order.setPaymentMode("RAZORPAY");

        orderRepository.save(order);
    }
	@Override
	public List<Orders> getAllOrders() {
		return orderRepository.findAll();
	}
}
