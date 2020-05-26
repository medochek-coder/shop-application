package com.medochek.shopapp.service.impl;

import com.medochek.shopapp.domain.ProductOrder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("OrderService should ")
@ExtendWith(SpringExtension.class)
@Import({OrderServiceImpl.class, ProductServiceImpl.class, BasketServiceImpl.class})
@DataJpaTest
class OrderServiceImplTest {
    @Autowired
    private OrderServiceImpl orderService;

    @Autowired
    private BasketServiceImpl basketService;

    @Test
    @DisplayName("create order")
    public void create() {
        ProductOrder order = ProductOrder.builder()
                .basket(basketService.getById(52L))
                .ownerFirstName("ownerFirstName")
                .ownerLastName("ownerLastName")
                .ownerPhone("1234567")
                .ownerEmail("gggg123@mail.com")
                .date(LocalDateTime.now())
                .status("IN_PROGRESS")
                .build();

        ProductOrder order1 = orderService.create(order);

        assertThat(order1).isNotNull();
        assertThat(order1.getId()).isNotNull();
        assertThat(order1.getBasket().getBasketRowList().isEmpty()).isFalse();
    }

    @Test
    @DisplayName("delete by id")
    public void shouldDeleteById() {
        orderService.delete(11L);

        assertThat(orderService.getById(11L)).isNull();
    }

    @Test
    @DisplayName("get orders in progress")
    public void getInProgressOrders() {
        List<ProductOrder> inProgressOrders = orderService.getInProgressOrders();

        assertThat(inProgressOrders.isEmpty()).isFalse();
        assertThat(inProgressOrders).hasSize(1);
        assertThat(inProgressOrders.get(0).getId()).isEqualTo(11L);
    }

    @Test
    @DisplayName("get completed orders")
    public void getCompletedOrders() {
        List<ProductOrder> completedOrders = orderService.getCompletedOrders();

        assertThat(completedOrders.isEmpty()).isFalse();
        assertThat(completedOrders).hasSize(1);
        assertThat(completedOrders.get(0).getId()).isEqualTo(12L);
    }

    @Test
    @DisplayName("get order by id")
    public void getOrderById() {
        ProductOrder order = ProductOrder.builder()
                .id(11L)
                .basket(basketService.getById(51L))
                .ownerFirstName("anna")
                .ownerLastName("petrova")
                .ownerPhone("1234567")
                .ownerEmail("anna@petr.com")
                .date(LocalDateTime.of(2020, 05, 26, 0, 0))
                .priceList("2-3.0;1-2.0")
                .status("IN_PROGRESS")
                .build();

        ProductOrder order1 = orderService.getById(11L);

        assertThat(order1).isNotNull();
        assertThat(order1.getId()).isEqualTo(order.getId());
        assertThat(order1.getStatus()).isEqualTo(order.getStatus());
        assertThat(order1.getBasket()).isEqualTo(order.getBasket());
        assertThat(order1.getDate()).isEqualTo(order.getDate());
        assertThat(order1.getOwnerEmail()).isEqualTo(order.getOwnerEmail());
        assertThat(order1.getOwnerFirstName()).isEqualTo(order.getOwnerFirstName());
        assertThat(order1.getOwnerLastName()).isEqualTo(order.getOwnerLastName());
        assertThat(order1.getOwnerPhone()).isEqualTo(order.getOwnerPhone());
        assertThat(order1.getPriceList()).isEqualTo(order.getPriceList());
    }

    @Test
    @DisplayName("complete order by id")
    public void completeById(){
        ProductOrder order = orderService.completeById(11L);

        assertThat(order).isNotNull();
        assertThat(order.getStatus()).isEqualTo("COMPLETE");
    }

    @Test
    @DisplayName("get all orders")
    public void getAll() {
        List<ProductOrder> orders = orderService.getAll();

        assertThat(orders.isEmpty()).isFalse();
        assertThat(orders).hasSize(2);
    }
}