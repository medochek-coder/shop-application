package com.medochek.shopapp.service.impl;

import com.medochek.shopapp.domain.Basket;
import com.medochek.shopapp.domain.Order;
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
    public Order create(Long basketId, String ownerFirstName, String ownerLastName, String ownerPhone, String ownerEmail) {

        Basket basket = basketService.getById(basketId);
        if(basket != null) {
            Order order = Order.builder()
                    .basket(basket)
                    .ownerFirstName(ownerFirstName)
                    .ownerLastName(ownerLastName)
                    .ownerPhone(ownerPhone)
                    .ownerEmail(ownerEmail)
                    .build();
            return repository.save(order);
        }
        else {
            return null;
        }

    }

    @Override
    public Order getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Order> getAll() {
        return repository.findAll();
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
