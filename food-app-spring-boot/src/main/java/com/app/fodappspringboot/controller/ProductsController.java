package com.app.fodappspringboot.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@ResponseBody
@RequestMapping("/api/v1/data/products/")
public class ProductsController {

    private Map<Integer, List<String>> tmpProducts;

    public ProductsController() {
        tmpProducts = new HashMap<>();
        tmpProducts.put(1, new ArrayList<>(Arrays.asList("product1", "12.99")));
        tmpProducts.put(2, new ArrayList<>(Arrays.asList("product2", "13.99")));
        tmpProducts.put(3, new ArrayList<>(Arrays.asList("product3", "14.99")));
    }

    @GetMapping("/all")
    public String getProducts() {
        String jsonString = null;
        ObjectMapper mapper  = new ObjectMapper();
        try {
            jsonString = mapper.writeValueAsString(tmpProducts);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return jsonString;
    }

    @GetMapping("/{id}")
    public String getProductById(@PathVariable int id) {
        String jsonString = null;
        ObjectMapper mapper  = new ObjectMapper();
        try {
            jsonString = mapper.writeValueAsString(tmpProducts.get(id));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return jsonString;
    }


}
