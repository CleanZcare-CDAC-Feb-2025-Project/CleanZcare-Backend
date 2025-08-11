package com.homecleaning.Entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
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
public class ServiceGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int displayOrder;
    private String iconPath;
    private String title;

    @ManyToMany(mappedBy = "group")
    @JsonBackReference
    private List<Category> category = new ArrayList();
    
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
        name = "service_group_service",
        joinColumns = @JoinColumn(name = "service_group_id"),
        inverseJoinColumns = @JoinColumn(name = "service_id")
    )
    @JsonManagedReference(value = "serviceGroupRef")
    private List<ServiceTable> services = new ArrayList<>();
}
