package com.app.fodappspringboot.controller;

import com.app.fodappspringboot.model.Product;
import com.app.fodappspringboot.repository.ProductRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@ResponseBody
@RequestMapping("/api/v1/data/products/")
public class ProductsController {

    private final ProductRepository productRepository;
    private final ObjectMapper mapper;

    public ProductsController(ProductRepository productRepository) {
        this.productRepository = productRepository;
        this.mapper = new ObjectMapper();

    }

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getProducts() throws JsonProcessingException {
        List<Product> result = productRepository.findAll();
        if (!result.isEmpty()) {
            String response = mapper.writeValueAsString(result);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(response);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getProductById(@PathVariable UUID id) throws JsonProcessingException {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            String response = mapper.writeValueAsString(product.get());
            return ResponseEntity
                    .ok()
                    .body(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = "/category/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getProductByCategoryName(@PathVariable String name) throws JsonProcessingException {
        List<Product> products = productRepository.findByCategoryName(name);
        if (!products.isEmpty()) {
            String response = mapper.writeValueAsString(products);
            return ResponseEntity
                    .ok()
                    .body(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
