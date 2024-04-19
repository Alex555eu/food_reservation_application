package com.app.fodappspringboot.repository;

import com.app.fodappspringboot.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<AppUser, UUID> {
}
