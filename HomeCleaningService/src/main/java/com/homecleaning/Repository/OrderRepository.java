package com.homecleaning.Repository;

import com.homecleaning.Entity.Customer;
import com.homecleaning.Entity.Orders;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Orders, Long> {

	Optional<Orders> findByRazorpayOrderId(String razorpayOrderId);
}
