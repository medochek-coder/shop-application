package com.medochek.shopapp.service.impl;

import com.medochek.shopapp.domain.Basket;
import com.medochek.shopapp.domain.BasketRow;
import com.medochek.shopapp.domain.Product;
import com.medochek.shopapp.repository.BasketRepository;
import com.medochek.shopapp.repository.BasketRowRepository;
import com.medochek.shopapp.service.BasketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BasketServiceImpl implements BasketService {
    @Autowired
    private BasketRepository repository;

    @Autowired
    private BasketRowRepository basketRowRepository;

    @Autowired
    private ProductServiceImpl productService;

    @Override
    public Basket createEmpty() {
        return repository.save(new Basket());
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
            BasketRow basketRows = basketRowRepository.findByBasketAndProduct(basket, product);
            if(basketRows == null) {
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
            BasketRow basketRows = basketRowRepository.findByBasketAndProduct(basket, product);
            if(basketRows == null) {
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

    /*@Override
    public Integer changeCountProductById(Long idBasket, Long idProduct,  Integer countProduct) {
        Basket basket = getById(idBasket);
        Product product = productService.getById(idProduct);
        if(basket != null && product != null) {
            BasketRow basketRow = basketRowRepository.findByBasketAndProduct(basket, product);
            if (basketRow == null && countProduct > 0) {
                addProductById(idBasket, idProduct, countProduct);
                return countProduct;
            }
            else {
                Integer count = basketRow.getCount() + countProduct;
                if (count > 0) {
                    basketRow.setCount(count);
                    return count;
                }
                else {
                    deleteProductById(idBasket, idProduct);
                    return 0;
                }

            }
        }
        return -1;
    }*/

    @Override
    public Integer changeCountProductById(Long idBasket, Long idProduct,  Integer countProduct) {
        Basket basket = getById(idBasket);
        Product product = productService.getById(idProduct);
        if(basket != null && product != null) {
            BasketRow basketRow = basketRowRepository.findByBasketAndProduct(basket, product);
            if (basketRow == null && countProduct > 0) {
                addProductById(idBasket, idProduct, countProduct);
                return countProduct;
            } else {
                if (countProduct > 0) {
                    basketRow.setCount(countProduct);
                    basketRowRepository.save(basketRow);
                    return countProduct;
                }
            }
        }
        return -1;
    }

    @Override
    public Integer incOrDecCountProductById(Long idBasket, Long idProduct, Boolean increase) {
        Basket basket = getById(idBasket);
        Product product = productService.getById(idProduct);
        if(basket != null && product != null) {
            BasketRow basketRow = basketRowRepository.findByBasketAndProduct(basket, product);
            if (basketRow == null && increase) {
                addProductById(idBasket, idProduct, 1);
                return 1;
            }
            if (basketRow == null && !increase) {
                return -1;
            }
            if(basketRow != null && increase) {
                changeCountProductById(idBasket, idProduct, basketRow.getCount() + 1);
                return basketRow.getCount() + 1;
            }
            else {
                Integer count = basketRow.getCount() + countProduct;
                if (count > 0) {
                    basketRow.setCount(count);
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
        Basket basket = getById(idBasket);
        Product product = productService.getById(idProduct);
        if(basket == null || product == null) return;
        BasketRow basketRow = basketRowRepository.findByBasketAndProduct(getById(idBasket), productService.getById(idProduct));
        if (basketRow != null) {
            basketRowRepository.deleteById(basketRow.getId());
        }
    }

    @Override
    public void clearById(Long id) {
        Basket basket = getById(id);
        if (basket == null) return;
        List<BasketRow> basketRows = basketRowRepository.findByBasket(basket);
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
