package com.app.fodappspringboot.service;

import com.app.fodappspringboot.model.*;
import com.app.fodappspringboot.repository.OrderDetailsRepository;
import com.app.fodappspringboot.repository.OrderItemRepository;
import com.app.fodappspringboot.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Service
public class OrdersService {

    private final OrderItemRepository orderItemRepository;
    private final OrderDetailsRepository orderDetailsRepository;
    private final ProductRepository productRepository;


    public ResponseEntity<String> createNewOrderItem(OrderDetails orderDetails, Integer quantity, UUID productId) {
        List<OrderItem> productsAlreadyInCart = orderItemRepository.findByOrderDetailsId(orderDetails.getId());
        Optional<OrderItem> item = productsAlreadyInCart.stream()
                .filter(e -> e.getProduct().getId().equals(productId))
                .findFirst();

        if (item.isPresent()) {
            item.get().setQuantity(item.get().getQuantity() + quantity);
            orderItemRepository.save(item.get());

        } else {
            Optional<Product> product = productRepository.findById(productId);
            if (product.isPresent()) {
                OrderItem orderItem = OrderItem.builder()
                        .product(product.get())
                        .orderDetails(orderDetails)
                        .quantity(quantity)
                        .build();
                orderItemRepository.save(orderItem);
            } else {
                return ResponseEntity.badRequest().build();
            }
        }
        reevaluateOrderDetails(orderDetails);

        return ResponseEntity.ok().build();
    }

    public OrderDetails findActiveOrderDetail(UUID userId) {
        List<OrderDetails> orderDetails = orderDetailsRepository.findAllByUserId(userId);
        return orderDetails.stream()
                .filter(e -> e.getProgressStatus() == 0)
                .findAny()
                .orElse(null);

    }

    public OrderDetails createNewOrderDetail(UUID userId) {
        OrderDetails orderDetails = OrderDetails.builder()
                .userId(userId)
                .total(BigDecimal.ZERO)
                .progressStatus(0)
                .build();
        orderDetailsRepository.save(orderDetails);
        return orderDetails;
    }

    public void removeOrderItemById(OrderDetails orderDetails, UUID id) {
        Optional<OrderItem> item = orderItemRepository.findById(id);
        item.ifPresent(orderItemRepository::delete);
        reevaluateOrderDetails(orderDetails);
    }

    public void removeAll(UUID userId) {
        OrderDetails orderDetails = findActiveOrderDetail(userId);
        if (orderDetails != null) {
            orderItemRepository.deleteAllByOrderDetailsId(orderDetails.getId());
            orderDetailsRepository.delete(orderDetails);
        }
    }

    private void reevaluateOrderDetails(OrderDetails orderDetails) {
        List<OrderItem> orderItems = orderItemRepository.findByOrderDetailsId(orderDetails.getId());
        Double sum = 0.0;
        for(OrderItem item : orderItems) {
            sum += item.getProduct().getPrice() * item.getQuantity();
        }
        orderDetails.setTotal(BigDecimal.valueOf(sum));
        orderDetailsRepository.save(orderDetails);
    }


}
