package com.homecleaning.Entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ServiceTable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;
    private double price;
    private String duration;
    private String description;

//    @ManyToOne
//    @JoinColumn(name = "group_id")
//    private ServiceGroup group;
    @ManyToOne
    @JoinColumn(name = "parent_id")
    private ServiceTable parentService;

    @OneToMany(mappedBy = "parentService", cascade = CascadeType.ALL)
    private List<ServiceTable> subServices = new ArrayList<>();
    @ManyToMany(mappedBy = "services")
    @JsonBackReference(value = "serviceGroupRef")
    private List<ServiceGroup> serviceGroups = new ArrayList<>();
}

