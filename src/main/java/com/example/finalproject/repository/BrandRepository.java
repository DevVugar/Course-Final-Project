package com.example.finalproject.repository;

import com.example.finalproject.model.entity.Brand;
import com.example.finalproject.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;


@Repository
public interface BrandRepository extends JpaRepository<Brand,Long> {


}
