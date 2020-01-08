package com.medochek.shopapp.repository;

import com.medochek.shopapp.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
