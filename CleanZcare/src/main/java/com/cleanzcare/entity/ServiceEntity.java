// file: com/cleanzcare/entity/ServiceEntity.java
package com.cleanzcare.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String serviceName;
    private String category;
    private String description;
    private double price;
}
