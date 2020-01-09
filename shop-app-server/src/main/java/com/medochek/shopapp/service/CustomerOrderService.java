package com.medochek.shopapp.service;

import com.medochek.shopapp.domain.CustomerOrder;

import java.util.List;

public interface CustomerOrderService {
    CustomerOrder create(Long basketId, String ownerFirstName, String ownerLastName, String ownerPhone, String ownerEmail);
    CustomerOrder getById(Long id);
    List<CustomerOrder> getAll();
    void delete(Long id);
}
