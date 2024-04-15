package com.app.fodappspringboot.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@ResponseBody
@RestController
@RequestMapping("/data/products")
public class ProductsController {

    Logger logger = LoggerFactory.getLogger(ProductsController.class); // to be injected or changed
    Map<Integer, List<String>> productsTMP; // tmp

    public ProductsController() {
        productsTMP = new HashMap<>();
        productsTMP.put(1, new ArrayList<>(Arrays.asList("product1","12.99")));
        productsTMP.put(2, new ArrayList<>(Arrays.asList("product2","11.99")));
        productsTMP.put(3, new ArrayList<>(Arrays.asList("product3","13.99")));
    }

    @GetMapping
    public String getAllProducts() {

        ObjectMapper mapper = new ObjectMapper();
        String jsonString = null;
        try {
            jsonString = mapper.writeValueAsString(productsTMP);
        } catch (Exception e) {
            logger.error("Object mapper exception");
        }

        return jsonString;
    }

    @GetMapping
    public String getProductById(@RequestParam("id") int productId){
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = null;
        try {
            jsonString = mapper.writeValueAsString(productsTMP.get(productId));
        } catch (Exception e) {
            logger.error("Object mapper exception");
        }

        return jsonString;
    }

}
