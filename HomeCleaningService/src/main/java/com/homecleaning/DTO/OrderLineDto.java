package com.homecleaning.DTO;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.homecleaning.Entity.Orders;

import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderLineDto {
    private BigDecimal price;
    private int quantity=1;
    private String title;
}



