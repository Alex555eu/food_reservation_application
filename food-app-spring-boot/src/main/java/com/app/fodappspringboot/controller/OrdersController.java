package com.app.fodappspringboot.controller;

import com.app.fodappspringboot.dto.AddProductToCartRequest;
import com.app.fodappspringboot.dto.MakeOrderRequest;
import com.app.fodappspringboot.model.OrderDetails;
import com.app.fodappspringboot.model.OrderItem;
import com.app.fodappspringboot.model.Store;
import com.app.fodappspringboot.model.User;
import com.app.fodappspringboot.repository.OrderDetailsRepository;
import com.app.fodappspringboot.repository.OrderItemRepository;
import com.app.fodappspringboot.repository.ProductRepository;
import com.app.fodappspringboot.repository.StoreRepository;
import com.app.fodappspringboot.service.OrdersService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@AllArgsConstructor
@ResponseBody
@RequestMapping("/api/v1/data/order/")
public class OrdersController {

    private final OrderDetailsRepository orderDetailsRepository;
    private final OrderItemRepository orderItemRepository;
    private final ProductRepository productRepository;
    private final OrdersService ordersService;
    private final StoreRepository storeRepository;

    @GetMapping(value = "/")
    public ResponseEntity<String> getAllOrderItems() throws JsonProcessingException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if ((authentication != null && authentication.getPrincipal() instanceof UserDetails)) {
            User user = (User) authentication.getPrincipal();
            OrderDetails orderDetails = ordersService.findActiveOrderDetail(user.getId());
            if (orderDetails != null) {
                List<OrderItem> list = orderItemRepository.findByOrderDetailsId(orderDetails.getId());
                ObjectMapper mapper = new ObjectMapper();
                String body = mapper.writeValueAsString(list);

                return ResponseEntity
                        .ok()
                        .body(body);
            }
        }
        return ResponseEntity.notFound().build();
    }



    @PutMapping(value = "/make")
    public ResponseEntity<String> makeOrder(@RequestBody MakeOrderRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if ((authentication != null && authentication.getPrincipal() instanceof UserDetails)) {
            User user = (User) authentication.getPrincipal();
            OrderDetails orderDetails = ordersService.findActiveOrderDetail(user.getId());

            if (orderDetails != null) {
                orderDetails.setProgressStatus(1);
                orderDetails.setPlacementDate(LocalDateTime.now());

                Optional<Store> store = storeRepository.findStoreById(request.storeId());
                store.ifPresent(orderDetails::setStore);

                orderDetailsRepository.save(orderDetails);
                return ResponseEntity
                        .status(HttpStatus.OK)
                        .build();
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> removeOrderItem(@PathVariable UUID id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if ((authentication != null && authentication.getPrincipal() instanceof UserDetails)) {
            User user = (User) authentication.getPrincipal();
            OrderDetails orderDetails = ordersService.findActiveOrderDetail(user.getId());
            if (orderDetails != null) {
                ordersService.removeOrderItemById(orderDetails, id);
            }
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping(value = "/")
    public ResponseEntity<String> removeAllOrderItems() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if ((authentication != null && authentication.getPrincipal() instanceof UserDetails)) {
            User user = (User) authentication.getPrincipal();
            OrderDetails orderDetails = ordersService.findActiveOrderDetail(user.getId());
            if (orderDetails != null) {
                ordersService.removeAll(user.getId());
            }
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping(value = "/add")
    public ResponseEntity<String> addProductToCart(@RequestBody AddProductToCartRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if ((authentication != null && authentication.getPrincipal() instanceof UserDetails)) {
            User user = (User) authentication.getPrincipal();

            OrderDetails orderDetails = ordersService.findActiveOrderDetail(user.getId());

            if (orderDetails != null) {
                return ordersService.createNewOrderItem(orderDetails, request.quantity(), request.productId());
            } else {
                OrderDetails newOrderDetails = ordersService.createNewOrderDetail(user.getId());
                return ordersService.createNewOrderItem(newOrderDetails, request.quantity(), request.productId());
            }
        }
        return ResponseEntity.notFound().build();
    }



}
