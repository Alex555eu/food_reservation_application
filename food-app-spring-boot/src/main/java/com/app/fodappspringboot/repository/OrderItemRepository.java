package com.app.fodappspringboot.repository;

import com.app.fodappspringboot.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

public interface OrderItemRepository extends JpaRepository<OrderItem, UUID> {

    List<OrderItem> findByOrderDetailsId(UUID id);

    @Transactional
    void deleteAllByOrderDetailsId(UUID id);

}
