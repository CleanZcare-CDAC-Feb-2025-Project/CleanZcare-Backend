package com.homecleaning.Entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale.Category;

import com.fasterxml.jackson.annotation.JsonBackReference;

import java.time.LocalDateTime;
import jakarta.persistence.*;
import jakarta.validation.constraints.Past;
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
//@ToString
public class ServiceModalResource {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ImageType imageType;

    private int imageOrder;
    private String imageUrl;
    private String caption;
    private String publicId;
    private String format;
    private int width;
    private int height;
    private long bytes;
    private LocalDateTime uploadedAt;

    @ManyToOne
    @JoinColumn(name = "service_module_id")
    @JsonBackReference(value = "resourceFromServiceModule")
    private ServiceModalTable serviceModule;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonBackReference(value = "resourceFromCategory")  // ✅ Added this
    private Category category;

    @ManyToMany(mappedBy = "modalResource")
    @JsonBackReference(value = "modalResources")
    private List<ServiceModalTable> list = new ArrayList<>();
}
