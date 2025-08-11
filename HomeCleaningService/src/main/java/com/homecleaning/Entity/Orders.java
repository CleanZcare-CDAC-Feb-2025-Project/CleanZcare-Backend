package com.homecleaning.Entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "orderId")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;
    @JsonManagedReference
    @OneToMany(mappedBy = "orders", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderLine> orderline;
    @ManyToOne
    @JoinColumn(name = "coupon_id")
    private Coupon coupon;
    private boolean isAvoidColling;
    private String orderDate;
    private String orderTime;
    private String address;
    private String phoneNo;
    private double taxRate;
    private BigDecimal tip;
    @Column(precision = 10, scale = 2)
    private BigDecimal totalPrice;
    @Column(nullable = false)
    private String paymentMode;
    @Enumerated(EnumType.STRING)
    private OrderStatus status = OrderStatus.CREATED;
    private String razorpayOrderId;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus = PaymentStatus.PENDING;
}
