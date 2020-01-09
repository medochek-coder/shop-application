package com.medochek.shopapp.repository;

import com.medochek.shopapp.domain.ProductOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<ProductOrder, Long> {
}
