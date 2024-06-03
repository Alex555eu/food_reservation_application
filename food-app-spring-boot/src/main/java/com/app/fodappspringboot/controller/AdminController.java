package com.app.fodappspringboot.controller;

import com.app.fodappspringboot.dto.AllOrdersResponseDto;
import com.app.fodappspringboot.model.OrderDetails;
import com.app.fodappspringboot.model.OrderItem;
import com.app.fodappspringboot.model.User;
import com.app.fodappspringboot.repository.OrderDetailsRepository;
import com.app.fodappspringboot.repository.OrderItemRepository;
import com.app.fodappspringboot.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/api/v1/admin")
public class AdminController {

    private final OrderDetailsRepository orderDetailsRepository;
    private final OrderItemRepository orderItemRepository;
    private final UserRepository userRepository;

    @GetMapping("/")
    public ResponseEntity<String> isAuthorized() {
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/all-orders")
    public ResponseEntity<String> getAllOrders() throws JsonProcessingException {
        List<AllOrdersResponseDto> responseBody = new ArrayList<>();
        List<OrderDetails> orderDetails = orderDetailsRepository.findAll();

        for(OrderDetails detail : orderDetails) {
            List<OrderItem> orderItems = orderItemRepository.findByOrderDetailsId(detail.getId());

            AllOrdersResponseDto responseDto = new AllOrdersResponseDto();

            responseDto.setPlacementDate(detail.getPlacementDate().toString());

            Optional<User> user = userRepository.findById(detail.getUserId());
            user.ifPresent(value -> responseDto.setEmail(value.getEmailAddress()));

            responseDto.setInfo(detail.getAdditionalInfo());
            responseDto.setTotal(detail.getTotal().toString());
            responseDto.setProgress(detail.getProgressStatus().toString());

            StringBuilder products = new StringBuilder();
            for(OrderItem item : orderItems) {
                products.append(" | ");
                products.append(item.getProduct().getName());
                products.append(" : $");
                products.append(item.getProduct().getPrice());
                products.append(" : ");
                products.append(item.getQuantity());
                products.append(" | ");
            }
            responseDto.setProducts(products.toString());
            responseBody.add(responseDto);
        }

        ObjectMapper mapper = new ObjectMapper();

        String body = mapper.writeValueAsString(responseBody);

        return ResponseEntity.ok().body(body);

    }

}
