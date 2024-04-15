package com.app.fodappspringboot.repository;

import com.app.fodappspringboot.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StoreRepository extends JpaRepository<Store, UUID> {
}
