package com.medochek.shopapp.repository;

import com.medochek.shopapp.domain.Basket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BasketRepository extends JpaRepository<Basket, Long> {
}
