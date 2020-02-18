package com.medochek.shopapp.service.impl;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

import com.medochek.shopapp.domain.Basket;
import com.medochek.shopapp.domain.BasketRow;
import com.medochek.shopapp.domain.Product;
import com.medochek.shopapp.repository.BasketRepository;
import com.medochek.shopapp.repository.BasketRowRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class BasketServiceImplTest {
    @InjectMocks
    private BasketServiceImpl service;

    @Mock
    private BasketRepository repository;

    @Mock
    private BasketRowRepository basketRowRepository;

    @Mock
    private ProductServiceImpl productService;

    private Basket basket;
    private Product product;
    private BasketRow basketRow;

    @Before
    public void init() {
        basket = Basket.builder()
                .id(1L)
                .basketRowList(new ArrayList<>())
                .build();
        product = Product.builder()
                .id(1L)
                .name("1")
                .description("1")
                .price(12d)
                .build();
        basketRow = BasketRow.builder()
                .id(1L)
                .basket(basket)
                .product(product)
                .count(1)
                .build();
        when(repository.findById(1L)).thenReturn(Optional.of(basket));
        when(productService.getById(1L)).thenReturn(product);
        when(basketRowRepository.save(basketRow)).thenReturn(basketRow);
    }

    @Test
    public void shouldProductAddedToBasket() {
        service.addProductById(1L,1L);

        assertThat(basketRow.getCount()).isEqualTo(1);
        assertThat(basketRow.getProduct()).isEqualTo(product);
        assertThat(basketRow.getBasket()).isEqualTo(basket);
    }

    @Test
    public void shouldProductCountBeIncremented() {
        when(basketRowRepository.findByBasketAndProduct(basket, product)).thenReturn(basketRow);

        Integer result = service.incOrDecCountProductById(1L, 1L, true);

        assertThat(result).isEqualTo(2);
    }

    @Test
    public void shouldProductCountBeDecremented() {
        BasketRow basketRow = BasketRow.builder()
                .id(1L)
                .basket(basket)
                .product(product)
                .count(6)
                .build();
        when(basketRowRepository.findByBasketAndProduct(basket, product)).thenReturn(basketRow);

        Integer result = service.incOrDecCountProductById(1L, 1L, false);

        assertThat(result).isEqualTo(5);
    }

    @Test
    public void shouldProductDeletedIfNewCount0() {
        when(basketRowRepository.findByBasketAndProduct(basket, product)).thenReturn(basketRow);

        Integer result = service.incOrDecCountProductById(1L, 1L, false);

        verify(basketRowRepository).deleteById(1L);
        assertThat(result).isEqualTo(0);
    }
}