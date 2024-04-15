package com.app.fodappspringboot.repository;

import com.app.fodappspringboot.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CartItemRepository extends JpaRepository<CartItem, UUID> {
}
