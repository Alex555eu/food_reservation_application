package com.app.fodappspringboot.repository;

import com.app.fodappspringboot.model.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderDetailsRepository extends JpaRepository<OrderDetails, UUID> {

    List<OrderDetails> findAllByUserId(UUID id);

}
