package com.homecleaning.Entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;

    @ManyToMany
    @JoinTable(
        name = "category_service_group",
        joinColumns = @JoinColumn(name = "category_id"),
        inverseJoinColumns = @JoinColumn(name = "service_group_id")
    )
    @JsonManagedReference
    private List<ServiceGroup> group = new ArrayList();

    @OneToMany(mappedBy = "category")
    @JsonBackReference(value = "resourceFromCategory")
    private List<ServiceModalResource> resources;
    
    
//    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<SlideData> slideDataList = new ArrayList<>();
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonBackReference(value = "category-slideData")

    private List<SlideData> slideDataList = new ArrayList<>();

}
