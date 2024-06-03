package com.app.fodappspringboot.controller;

import com.app.fodappspringboot.model.Store;
import com.app.fodappspringboot.repository.StoreRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/data/store")
public class StoreController {

    private final StoreRepository storeRepository;

    @GetMapping(value = "/")
    public ResponseEntity<String> getAllStores() throws JsonProcessingException {
        List<Store> stores = storeRepository.findAll();
        ObjectMapper mapper = new ObjectMapper();

        String body = mapper.writeValueAsString(stores);
        if (!body.isEmpty()) {
            return ResponseEntity.ok().body(body);
        }
        return ResponseEntity.notFound().build();
    }


}
