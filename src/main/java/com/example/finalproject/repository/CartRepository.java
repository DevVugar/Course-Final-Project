package com.example.finalproject.repository;

import com.example.finalproject.model.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart,Long> {

    Cart findByBankAccount(String bankAccount);
}
