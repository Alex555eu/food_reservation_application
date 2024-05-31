package com.app.fodappspringboot;

import com.app.fodappspringboot.model.Product;
import com.app.fodappspringboot.model.ProductCategory;
import com.app.fodappspringboot.model.Role;
import com.app.fodappspringboot.model.User;
import com.app.fodappspringboot.repository.ProductCategoryRepository;
import com.app.fodappspringboot.repository.ProductRepository;
import com.app.fodappspringboot.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NonNull;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.*;

@AllArgsConstructor
@Component
public class StartupApplicationListener implements ApplicationListener<ApplicationReadyEvent> {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final ProductCategoryRepository productCategoryRepository;

    @Override
    public void onApplicationEvent(@NonNull ApplicationReadyEvent event) {
        User user = User.builder()
                .firstName("tohru")
                .lastName("kobayashi")
                .emailAddress("dragon@maid.com")
                .password(passwordEncoder.encode("password"))
                .role(Role.USER)
                .build();
        userRepository.save(user);


        ProductCategory donut = ProductCategory.builder()
                .name("donut")
                .description("category for donuts")
                .build();
        productCategoryRepository.save(donut);

        ProductCategory drink = ProductCategory.builder()
                .name("drink")
                .description("category for drinks")
                .build();
        productCategoryRepository.save(drink);

        Set<ProductCategory> donutSet = new HashSet<>();
        donutSet.add(donut);

        Set<ProductCategory> drinkSet = new HashSet<>();
        drinkSet.add(drink);

        Product product = Product.builder()
                .price(12.0)
                .description("test description")
                .name("d'oh nut")
                .disabled(false)
                .imagePath("../../../assets/public/product-images/D_Oh_Nut_1024x1024.jpg")
                .productCategories(donutSet)
                .build();
        productRepository.save(product);


        Product product2 = Product.builder()
                .price(12.0)
                .description("test description")
                .name("bruno mars")
                .disabled(false)
                .imagePath("../../../assets/public/product-images/Bruno_Mars.1_1024x1024.webp")
                .productCategories(donutSet)
                .build();
        productRepository.save(product2);

        Product product3 = Product.builder()
                .price(12.0)
                .description("test description")
                .name("caramel milk shake")
                .disabled(false)
                .imagePath("../../../assets/public/product-images/caramelmilkshake.jpg")
                .productCategories(drinkSet)
                .build();
        productRepository.save(product3);




        Product product4 = Product.builder()
                .price(12.0)
                .description("test description")
                .name("david hasselhoff")
                .disabled(false)
                .imagePath("../../../assets/public/product-images/David_Hasselhoff_1024x1024.jpg")
                .productCategories(donutSet)
                .build();
        productRepository.save(product4);

        Product product5 = Product.builder()
                .price(12.0)
                .description("test description")
                .name("i make honey moves")
                .disabled(false)
                .imagePath("../../../assets/public/product-images/I_Make_Honey_Moves_8a8ab8e2-2225-47da-bf6b-012f45a1ffe2_1024x1024.jpg")
                .productCategories(donutSet)
                .build();
        productRepository.save(product5);

        Product product6 = Product.builder()
                .price(12.0)
                .description("test description")
                .name("ice bae")
                .disabled(false)
                .imagePath("../../../assets/public/product-images/Ice_Ice_Bae_Bae_1024x1024.jpg")
                .productCategories(donutSet)
                .build();
        productRepository.save(product6);

        Product product7 = Product.builder()
                .price(12.0)
                .description("test description")
                .name("ice latte")
                .disabled(false)
                .imagePath("../../../assets/public/product-images/icelatte.jpg")
                .productCategories(drinkSet)
                .build();
        productRepository.save(product7);

        Product product8 = Product.builder()
                .price(12.0)
                .description("test description")
                .name("life on mars")
                .disabled(false)
                .imagePath("../../../assets/public/product-images/Life_On_Mars_be8b214c-2817-4e11-a8c2-a2b149cd1872_1024x1024.webp")
                .productCategories(donutSet)
                .build();
        productRepository.save(product8);

        Product product9 = Product.builder()
                .price(12.0)
                .description("test description")
                .name("love at first bite")
                .disabled(false)
                .imagePath("../../../assets/public/product-images/Love_At_First_Bite.jpg")
                .productCategories(donutSet)
                .build();
        productRepository.save(product9);

        Product product10 = Product.builder()
                .price(12.0)
                .description("test description")
                .name("original")
                .disabled(false)
                .imagePath("../../../assets/public/product-images/OG_3744b222-303b-4c00-b485-385b00e2117c_1024x1024.jpg")
                .productCategories(donutSet)
                .build();
        productRepository.save(product10);

        Product product11 = Product.builder()
                .price(12.0)
                .description("test description")
                .name("pinacolada")
                .disabled(false)
                .imagePath("../../../assets/public/product-images/pinacolada.jpg")
                .productCategories(drinkSet)
                .build();
        productRepository.save(product11);

        Product product12 = Product.builder()
                .price(12.0)
                .description("test description")
                .name("sia later")
                .disabled(false)
                .imagePath("../../../assets/public/product-images/Sia_Later_1024x1024.webp")
                .productCategories(donutSet)
                .build();
        productRepository.save(product12);
    }
}