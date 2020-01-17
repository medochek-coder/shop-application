package com.medochek.shopapp.repository;

import com.medochek.shopapp.domain.Basket;
import com.medochek.shopapp.domain.BasketRow;
import com.medochek.shopapp.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BasketRowRepository extends JpaRepository<BasketRow, Long> {
    BasketRow findByBasketAndProduct(Basket basket, Product product);
    List<BasketRow> findByBasket(Basket basket);
}
