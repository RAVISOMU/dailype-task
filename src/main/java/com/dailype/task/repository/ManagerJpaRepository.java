package com.dailype.task.repository;


import com.dailype.task.model.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManagerJpaRepository extends JpaRepository<Manager, Integer> {
    Manager findByManagerId(int managerId);
}
