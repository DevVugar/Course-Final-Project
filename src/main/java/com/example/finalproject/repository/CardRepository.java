package com.example.finalproject.repository;

import com.example.finalproject.model.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card,Long> {

    Card findByBankAccount(String bankAccount);
}
