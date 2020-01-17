package com.medochek.shopapp.repository;

import com.medochek.shopapp.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByPriceSaleIsNotNull();
    List<Product> findByPriceSaleIsNull();
}
