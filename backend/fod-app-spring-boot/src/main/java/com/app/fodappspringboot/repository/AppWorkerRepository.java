package com.app.fodappspringboot.repository;

import com.app.fodappspringboot.model.AppWorker;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AppWorkerRepository extends JpaRepository<AppWorker, UUID> {
}
