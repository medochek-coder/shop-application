package com.medochek.shopapp.service.impl;

import com.medochek.shopapp.domain.Basket;
import com.medochek.shopapp.domain.BasketRow;
import com.medochek.shopapp.domain.Product;
import com.medochek.shopapp.repository.BasketRepository;
import com.medochek.shopapp.repository.BasketRowRepository;
import com.medochek.shopapp.service.BasketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BasketServiceImpl implements BasketService {
    @Autowired
    private BasketRepository repository;

    @Autowired
    private BasketRowRepository basketRowRepository;

    @Autowired
    private ProductServiceImpl productService;

    @Autowired
    private BasketRepository basketRepository;

    @Override
    public Basket createEmpty() {
        return basketRepository.save(new Basket());
    }

    @Override
    public void deleteById(Long id) {
        Basket basket = getById(id);
        if (basket != null) {
            repository.deleteById(id);
        }
    }

    @Override
    public void addProductById(Long idBasket, Long idProduct) {
        Basket basket = getById(idBasket);
        Product product = productService.getById(idProduct);
        if(basket != null && product != null) {
            List<BasketRow> basketRows = basketRowRepository.findByBasketAndAndProduct(basket, product);
            if(basketRows.isEmpty()) {
                BasketRow basketRow = BasketRow.builder()
                        .basket(basket)
                        .product(product)
                        .count(1)
                        .build();
                basketRowRepository.save(basketRow);
            }
            else {
                changeCountProductById(idBasket, idProduct, 1);
            }

        }

    }

    @Override
    public void addProductById(Long idBasket, Long idProduct, Integer count) {
        Basket basket = getById(idBasket);
        Product product = productService.getById(idProduct);
        if(basket != null && product != null) {
            List<BasketRow> basketRows = basketRowRepository.findByBasketAndAndProduct(basket, product);
            if(basketRows.isEmpty()) {
                BasketRow basketRow = BasketRow.builder()
                        .basket(basket)
                        .product(product)
                        .count(count)
                        .build();
                basketRowRepository.save(basketRow);
            }
            else {
                changeCountProductById(idBasket, idProduct, count);
            }

        }

    }

    @Override
    public Integer changeCountProductById(Long idBasket, Long idProduct,  Integer countProduct) {
        Basket basket = getById(idBasket);
        Product product = productService.getById(idProduct);
        if(basket != null && product != null) {
            List<BasketRow> basketRows = basketRowRepository.findByBasketAndAndProduct(basket, product);
            if (basketRows.isEmpty() && countProduct > 0) {
                addProductById(idBasket, idProduct, countProduct);
                return countProduct;
            }
            else {
                Integer count = basketRows.get(0).getCount() + countProduct;
                if (count > 0) {
                    basketRows.get(0).setCount(count);
                    basketRowRepository.save(basketRows.get(0));
                    return count;
                }
                else {
                    deleteProductById(idBasket, idProduct);
                    return 0;
                }

            }
        }
        return -1;
    }

    @Override
    public void deleteProductById(Long idBasket, Long idProduct) {
        List<BasketRow> basketRows = basketRowRepository.findByBasketAndAndProduct(getById(idBasket), productService.getById(idProduct));
        for (BasketRow row : basketRows) {
            basketRowRepository.deleteById(row.getId());
        }
    }

    @Override
    public void clearById(Long id) {
        List<BasketRow> basketRows = basketRowRepository.findByBasket(getById(id));
        for (BasketRow row : basketRows) {
            basketRowRepository.deleteById(row.getId());
        }
    }

    @Override
    public Basket getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Basket> getAll() {
        return repository.findAll();
    }

}
