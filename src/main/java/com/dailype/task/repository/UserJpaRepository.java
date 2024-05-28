package com.dailype.task.repository;

import com.dailype.task.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<User, Integer> {
    User findByUserId(int userId);
    User findByMobNum(String mobNum);
}
