package com.example.finalproject.repository;

import com.example.finalproject.model.entity.Product;
import com.example.finalproject.model.entity.Review;
import com.example.finalproject.model.entity.WishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface  ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findByProduct(Product product);

}
