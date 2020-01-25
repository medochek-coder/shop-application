package com.medochek.shopapp.service.impl;

import com.medochek.shopapp.domain.Basket;
import com.medochek.shopapp.domain.ProductOrder;
import com.medochek.shopapp.repository.OrderRepository;
import com.medochek.shopapp.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private BasketServiceImpl basketService;

    @Autowired
    private OrderRepository repository;

    @Override
    public ProductOrder create(ProductOrder productOrder) {
        return repository.save(productOrder);
    }

    @Override
    public ProductOrder getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<ProductOrder> getAll() {
        return repository.findAll();
    }

    @Override
    public List<ProductOrder> getInProgressOrders() {
        return repository.findByStatus("IN_PROGRESS");
    }

    @Override
    public List<ProductOrder> getCompletedOrders() {
        return repository.findByStatus("COMPLETE");
    }

    @Override
    public ProductOrder completeById(Long orderId) {
        ProductOrder productOrder = getById(orderId);
        productOrder.setStatus("COMPLETE");
        return repository.save(productOrder);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
