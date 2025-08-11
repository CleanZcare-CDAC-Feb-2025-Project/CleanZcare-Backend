package com.homecleaning.DTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.homecleaning.Entity.Coupon;
import com.homecleaning.Entity.Customer;
import com.homecleaning.Entity.OrderLine;
import com.homecleaning.Entity.OrderStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDto {

	private List<OrderLineDto> orderline;
	private BigDecimal totalPrice;
	private String address;
	private BigDecimal taxRate; // Removed '%', keep it as decimal or computed on backend
	private BigDecimal tip;
	private String phoneNo;
	private String coupanCode; // Coupon code entered by user

	private boolean isAvoidColling;

	private Long customerId;

	private String orderDate; // Format: "yyyy-MM-dd"
	private String orderTime; // Format: "HH:mm"

}
