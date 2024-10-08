package com.example.finalproject.repository;

import com.example.finalproject.model.entity.Order;
import com.example.finalproject.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<Order> findByOrders(User user);
}
