package com.homecleaning.Entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;
    private String title;
    private String subTitle;
    private String discription;
    private BigDecimal minamt;
    private BigDecimal discount;
    private LocalDateTime expirationDate;
    private boolean isActive;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "term_group_id", referencedColumnName = "id")
    private TermGroup terms;
}

  
