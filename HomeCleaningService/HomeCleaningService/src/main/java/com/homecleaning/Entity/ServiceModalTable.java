package com.homecleaning.Entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale.Category;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ServiceModalTable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;
    private String description;
    private String tag;

    // ServiceModalTable --> ShowCategory (OneToMany)
    @OneToMany(mappedBy = "serviceModalTable", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "serviceModalRef")
    private List<ShowCategory> showCategories = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
        name = "service_modal_table_resources",
        joinColumns = @JoinColumn(name = "modal_table_id"),
        inverseJoinColumns = @JoinColumn(name = "resource_id")
    )
    private List<ServiceModalResource> modalResource = new ArrayList<>();
}
