package com.homecleaning.Service;

import java.util.List;

import com.homecleaning.DTO.OrderDto;
import com.homecleaning.DTO.PaymentVerificationDto;
import com.homecleaning.Entity.Orders;

public interface OrderService {
    Orders placeOrder(OrderDto orderDto);
    void verifyAndUpdatePayment(PaymentVerificationDto paymentDto) throws Exception;
	List<Orders> getAllOrders();

}
