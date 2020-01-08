package com.medochek.shopapp.service;

import com.medochek.shopapp.domain.Order;

import java.util.List;

public interface OrderService {
    Order create(Long basketId, String ownerFirstName, String ownerLastName, String ownerPhone, String ownerEmail);
    Order getById(Long id);
    List<Order> getAll();
    void delete(Long id);
}
