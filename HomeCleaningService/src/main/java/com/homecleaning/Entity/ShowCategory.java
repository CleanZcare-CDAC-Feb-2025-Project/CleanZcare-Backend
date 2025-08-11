package com.homecleaning.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ShowCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;
    private String iconUrl;
    private String imageUrl;
    private String slug;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

 // ManyToMany with Page
    @ManyToMany(mappedBy = "showCategoryList", cascade = CascadeType.PERSIST)
    @JsonBackReference(value = "pageShowCategoryRef")
    private List<Page> pages = new ArrayList<>();

    // Self-referencing Parent
    @ManyToOne
    @JoinColumn(name = "parent_category_id")
    @JsonBackReference(value = "subCategoryRef")
    private ShowCategory parentCategory;

    // Self-referencing Children
    @OneToMany(mappedBy = "parentCategory", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "subCategoryRef")
    private List<ShowCategory> subCategories = new ArrayList<>();

    // ShowCategory --> ServiceModalTable (ManyToOne)
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "service_modal_table_id")
    @JsonManagedReference(value = "serviceModalRef")
    private ServiceModalTable serviceModalTable;
}
