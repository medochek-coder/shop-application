package com.medochek.shopapp.repository;

import com.medochek.shopapp.domain.BasketRow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BasketRowRepository extends JpaRepository<BasketRow, Long> {
    List<BasketRow> findByBasketAndAndProduct(Long basketId, Long productId);
    List<BasketRow> findByBasket(Long basketId);
}
