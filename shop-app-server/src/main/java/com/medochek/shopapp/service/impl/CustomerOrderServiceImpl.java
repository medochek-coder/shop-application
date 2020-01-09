package com.medochek.shopapp.service.impl;

import com.medochek.shopapp.domain.Basket;
import com.medochek.shopapp.domain.CustomerOrder;
import com.medochek.shopapp.repository.CustomerOrderRepository;
import com.medochek.shopapp.service.CustomerOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CustomerOrderServiceImpl implements CustomerOrderService {

    @Autowired
    private BasketServiceImpl basketService;

    @Autowired
    private CustomerOrderRepository repository;

    @Override
    public CustomerOrder create(Long basketId, String ownerFirstName, String ownerLastName, String ownerPhone, String ownerEmail) {

        Basket basket = basketService.getById(basketId);
        if(basket != null) {
            CustomerOrder customerOrder = CustomerOrder.builder()
                    .basket(basket)
                    .ownerFirstName(ownerFirstName)
                    .ownerLastName(ownerLastName)
                    .ownerPhone(ownerPhone)
                    .ownerEmail(ownerEmail)
                    .build();
            return repository.save(customerOrder);
        }
        else {
            return null;
        }

    }

    @Override
    public CustomerOrder getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<CustomerOrder> getAll() {
        return repository.findAll();
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
