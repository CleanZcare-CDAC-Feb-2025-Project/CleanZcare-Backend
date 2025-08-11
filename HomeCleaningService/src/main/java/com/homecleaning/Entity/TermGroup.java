package com.homecleaning.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TermGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;  // "Terms and Conditions"

    @OneToMany(mappedBy = "termGroup", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Terms> data = new ArrayList<>();
}
