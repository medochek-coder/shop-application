package com.medochek.shopapp.service;

import com.medochek.shopapp.domain.ProductOrder;

import java.util.List;

public interface OrderService {
    ProductOrder create(ProductOrder productOrder);
    ProductOrder getById(Long id);
    List<ProductOrder> getAll();
    List<ProductOrder> getInProgressOrders();
    List<ProductOrder> getCompletedOrders();
    ProductOrder completeById(Long orderId);
    void delete(Long id);

}
