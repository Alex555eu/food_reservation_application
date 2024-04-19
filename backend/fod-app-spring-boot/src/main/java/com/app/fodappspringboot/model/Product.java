package com.app.fodappspringboot.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.util.Set;
import java.util.UUID;

@Data
@Entity
public class Product {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    private String name;

    private String description;

    private Double price;

    private String imagePath;

    private Boolean disabled;

    @ManyToMany
    @JoinTable(
        name = "product_category_association",
        joinColumns = @JoinColumn(name = "product_id"),
        inverseJoinColumns = @JoinColumn(name = "product_category_id"))
    private Set<ProductCategory> productCategories;

}
