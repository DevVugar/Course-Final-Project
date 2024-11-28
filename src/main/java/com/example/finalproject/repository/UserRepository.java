package com.example.finalproject.repository;

import com.example.finalproject.model.entity.Payment;
import com.example.finalproject.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByFullName(String name);

    Optional<User> findByUsername(String username);
}
