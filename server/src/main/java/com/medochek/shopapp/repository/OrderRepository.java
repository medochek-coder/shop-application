package com.medochek.shopapp.repository;

import com.medochek.shopapp.domain.ProductOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<ProductOrder, Long> {
    List<ProductOrder> findByStatus(String status);
}
