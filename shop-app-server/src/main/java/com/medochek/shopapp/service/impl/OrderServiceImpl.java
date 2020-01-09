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
    public ProductOrder create(Long basketId, String ownerFirstName, String ownerLastName, String ownerPhone, String ownerEmail) {

        Basket basket = basketService.getById(basketId);
        if(basket != null) {
            ProductOrder productOrder = ProductOrder.builder()
                    .basket(basket)
                    .ownerFirstName(ownerFirstName)
                    .ownerLastName(ownerLastName)
                    .ownerPhone(ownerPhone)
                    .ownerEmail(ownerEmail)
                    .build();
            return repository.save(productOrder);
        }
        else {
            return null;
        }

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
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
