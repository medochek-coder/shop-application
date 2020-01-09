package com.medochek.shopapp.service;

import com.medochek.shopapp.domain.ProductOrder;

import java.util.List;

public interface OrderService {
    ProductOrder create(Long basketId, String ownerFirstName, String ownerLastName, String ownerPhone, String ownerEmail);
    ProductOrder getById(Long id);
    List<ProductOrder> getAll();
    void delete(Long id);
}
