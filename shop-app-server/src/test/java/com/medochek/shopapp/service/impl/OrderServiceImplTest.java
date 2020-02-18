package com.medochek.shopapp.service.impl;

import com.medochek.shopapp.domain.Basket;
import com.medochek.shopapp.domain.BasketRow;
import com.medochek.shopapp.domain.Product;
import com.medochek.shopapp.domain.ProductOrder;
import com.medochek.shopapp.repository.OrderRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceImplTest {
    @InjectMocks
    private OrderServiceImpl service;
    @Mock
    private OrderRepository repository;

    @Test
    public void shouldProductOrderBeCompleted() {
        Basket basket = Basket.builder()
                .id(1L)
                .basketRowList(new ArrayList<>())
                .build();
        Product product = Product.builder()
                .id(1L)
                .name("1")
                .description("1")
                .price(12d)
                .build();
        BasketRow basketRow = BasketRow.builder()
                .id(1L)
                .basket(basket)
                .product(product)
                .count(1)
                .build();
        List<BasketRow> basketRows = new ArrayList<>();
        basketRows.add(basketRow);
        basket.setBasketRowList(basketRows);
        ProductOrder productOrder = ProductOrder.builder()
                .id(1L)
                .basket(basket)
                .ownerFirstName("2")
                .ownerLastName("a")
                .ownerEmail("b")
                .ownerPhone("5")
                .status("IN_PROGRESS")
                .date(LocalDateTime.now())
                .build();
        when(repository.findById(1L)).thenReturn(Optional.of(productOrder));
        ProductOrder productOrder2 = ProductOrder.builder()
                .id(1L)
                .basket(basket)
                .ownerFirstName("2")
                .ownerLastName("a")
                .ownerEmail("b")
                .ownerPhone("5")
                .status("COMPLETE")
                .date(LocalDateTime.now())
                .build();
        when(repository.findById(1L)).thenReturn(Optional.of(productOrder));
        when(repository.save(productOrder)).thenReturn(productOrder2);

        ProductOrder result = service.completeById(1L);

        assertThat(result).isNotNull().isEqualTo(productOrder2);
        assertThat(result.getStatus()).isNotEmpty().isEqualTo("COMPLETE");
    }
}