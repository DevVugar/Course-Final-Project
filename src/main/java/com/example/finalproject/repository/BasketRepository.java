package com.example.finalproject.repository;

import com.example.finalproject.model.entity.Basket;
import com.example.finalproject.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BasketRepository extends JpaRepository<Basket,Long> {

}
