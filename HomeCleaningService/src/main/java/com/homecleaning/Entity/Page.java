package com.homecleaning.Entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.*;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Page {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String slug;
    private boolean showCategory;
    private String imageUrl;
    private String description;
    
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
        name = "page_show_category",
        joinColumns = @JoinColumn(name = "page_id"),
        inverseJoinColumns = @JoinColumn(name = "show_category_id")
    )
    @JsonManagedReference(value = "pageShowCategoryRef")
    private List<ShowCategory> showCategoryList = new ArrayList();

}
