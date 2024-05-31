package com.app.fodappspringboot.repository;

import com.app.fodappspringboot.model.Product;
import com.app.fodappspringboot.model.ProductCategory;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {

    @Query("SELECT p FROM Product p JOIN p.productCategories pc WHERE pc.name = :categoryName")
    List<Product> findByCategoryName(String categoryName);

}
