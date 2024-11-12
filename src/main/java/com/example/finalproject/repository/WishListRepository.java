package com.example.finalproject.repository;

import com.example.finalproject.model.entity.User;
import com.example.finalproject.model.entity.WishList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishListRepository extends JpaRepository<WishList,Long> {
 WishList findByUser(User u);
}
