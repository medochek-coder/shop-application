package com.medochek.shopapp.service.impl;

import com.medochek.shopapp.domain.Basket;
import com.medochek.shopapp.domain.BasketRow;
import com.medochek.shopapp.domain.Product;
import com.medochek.shopapp.repository.BasketRowRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("BasketService should ")
@ExtendWith(SpringExtension.class)
@Import({BasketServiceImpl.class, ProductServiceImpl.class})
@DataJpaTest
class BasketServiceImplTest {
    @Autowired
    private BasketServiceImpl basketService;

    @Autowired
    private BasketRowRepository basketRowRepository;

    @Test
    @DisplayName("create empty basket")
    public void createEmptyBasket() {
        Basket basket = basketService.createEmpty();

        assertThat(basket).isNotNull();
        assertThat(basket.getId()).isNotNull();
        assertThat(basket.getBasketRowList()).isNullOrEmpty();
    }

    @Test
    @DisplayName("get all baskets")
    public void getAll() {
        List<Basket> basketList = basketService.getAll();

        assertThat(basketList.isEmpty()).isFalse();
        assertThat(basketList).hasSize(12);
    }

    @Test
    @DisplayName("delete by id")
    public void shouldDeleteById() {
        basketService.deleteById(1L);

        assertThat(basketService.getById(1L)).isNull();
    }

    @Test
    @DisplayName("create basket row and add 1 product by basket and product ids")
    public void add1ProductById() {
        Product p2 = Product.builder()
                .id(2L)
                .name("Пирог 2")
                .description("Вкусный 2")
                .price(3.0)
                .priceSale(2.5)
                .build();

        basketService.addProductById(1L, 2L);

        BasketRow basketRow = basketRowRepository.findByBasket(basketService.getById(1L)).get(0);
        Basket basket = basketService.getById(1L);
        assertThat(basketRow).isNotNull();
        assertThat(basket.getBasketRowList()).isNotNull();
        assertThat(basket.getBasketRowList()).hasSize(1);
        assertThat(basket.getBasketRowList().get(0).getProduct()).isEqualTo(p2);
        assertThat(basket.getBasketRowList().get(0).getCount()).isEqualTo(1);
    }

    @Test
    @DisplayName("set 1 product by basket and product ids")
    public void addProductById() {
        Product p2 = Product.builder()
                .id(2L)
                .name("Пирог 2")
                .description("Вкусный 2")
                .price(3.0)
                .priceSale(2.5)
                .build();

        basketService.addProductById(2L, 2L);

        BasketRow basketRow = basketRowRepository.findByBasket(basketService.getById(2L)).get(0);
        Basket basket = basketService.getById(2L);
        assertThat(basketRow).isNotNull();
        assertThat(basket.getBasketRowList()).isNotNull();
        assertThat(basket.getBasketRowList()).hasSize(1);
        assertThat(basket.getBasketRowList().get(0).getId()).isEqualTo(99);
        assertThat(basket.getBasketRowList().get(0).getProduct()).isEqualTo(p2);
        assertThat(basket.getBasketRowList().get(0).getCount()).isEqualTo(1);
    }

    @Test
    @DisplayName("set count product by basket and product ids")
    public void changeCountProductById() {
        Product p2 = Product.builder()
                .id(2L)
                .name("Пирог 2")
                .description("Вкусный 2")
                .price(3.0)
                .priceSale(2.5)
                .build();

        Integer integer = basketService.changeCountProductById(3L, 2L, 20);

        BasketRow basketRow = basketRowRepository.findByBasket(basketService.getById(3L)).get(0);
        Basket basket = basketService.getById(3L);
        assertThat(basketRow).isNotNull();
        assertThat(integer).isEqualTo(20);
        assertThat(basket.getBasketRowList()).isNotNull();
        assertThat(basket.getBasketRowList()).hasSize(1);
        assertThat(basket.getBasketRowList().get(0).getId()).isEqualTo(98);
        assertThat(basket.getBasketRowList().get(0).getProduct()).isEqualTo(p2);
        assertThat(basket.getBasketRowList().get(0).getCount()).isEqualTo(20);
    }

    @Test
    @DisplayName("create basket row with need count product")
    public void addCountProductById() {
        Product p2 = Product.builder()
                .id(2L)
                .name("Пирог 2")
                .description("Вкусный 2")
                .price(3.0)
                .priceSale(2.5)
                .build();

        Integer integer = basketService.changeCountProductById(44L, 2L, 20);

        BasketRow basketRow = basketRowRepository.findByBasket(basketService.getById(44L)).get(0);
        Basket basket = basketService.getById(44L);
        assertThat(basketRow).isNotNull();
        assertThat(integer).isEqualTo(20);
        assertThat(basket.getBasketRowList()).isNotNull();
        assertThat(basket.getBasketRowList()).hasSize(1);
        assertThat(basket.getBasketRowList().get(0).getProduct()).isEqualTo(p2);
        assertThat(basket.getBasketRowList().get(0).getCount()).isEqualTo(20);
    }

    @Test
    @DisplayName("delete basket row with product")
    public void deleteBasketRowIfCountProductNegative() {
        Integer integer = basketService.changeCountProductById(45L, 2L, -3);

        List<BasketRow> basketRows = basketRowRepository.findByBasket(basketService.getById(45L));
        Basket basket = basketService.getById(45L);

        assertThat(integer).isEqualTo(0);
        assertThat(basket.getBasketRowList()).isNullOrEmpty();
    }

    @Test
    @DisplayName("append need count product by basket and product ids")
    public void appendProductById() {
        Product p2 = Product.builder()
                .id(2L)
                .name("Пирог 2")
                .description("Вкусный 2")
                .price(3.0)
                .priceSale(2.5)
                .build();

        basketService.addProductById(46L, 2L, 100);

        BasketRow basketRow = basketRowRepository.findByBasket(basketService.getById(46L)).get(0);
        Basket basket = basketService.getById(46L);
        assertThat(basketRow).isNotNull();
        assertThat(basket.getBasketRowList()).isNotNull();
        assertThat(basket.getBasketRowList()).hasSize(1);
        assertThat(basket.getBasketRowList().get(0).getId()).isEqualTo(96);
        assertThat(basket.getBasketRowList().get(0).getProduct()).isEqualTo(p2);
        assertThat(basket.getBasketRowList().get(0).getCount()).isEqualTo(102);
    }

    @Test
    @DisplayName("create basket row with need count product")
    public void addCountProductById2() {
        Product p2 = Product.builder()
                .id(2L)
                .name("Пирог 2")
                .description("Вкусный 2")
                .price(3.0)
                .priceSale(2.5)
                .build();

        basketService.addProductById(47L, 2L, 100);

        BasketRow basketRow = basketRowRepository.findByBasket(basketService.getById(47L)).get(0);
        Basket basket = basketService.getById(47L);
        assertThat(basketRow).isNotNull();
        assertThat(basket.getBasketRowList()).isNotNull();
        assertThat(basket.getBasketRowList()).hasSize(1);
        assertThat(basket.getBasketRowList().get(0).getProduct()).isEqualTo(p2);
        assertThat(basket.getBasketRowList().get(0).getCount()).isEqualTo(100);
    }

    @Test
    @DisplayName("+1 count product")
    public void incCountProductById() {
        Product p2 = Product.builder()
                .id(2L)
                .name("Пирог 2")
                .description("Вкусный 2")
                .price(3.0)
                .priceSale(2.5)
                .build();

        Integer integer = basketService.incOrDecCountProductById(48L, 2L, true);

        BasketRow basketRow = basketRowRepository.findByBasket(basketService.getById(48L)).get(0);
        Basket basket = basketService.getById(48L);

        assertThat(basketRow).isNotNull();
        assertThat(basketRow.getId()).isEqualTo(95);
        assertThat(basket.getBasketRowList()).isNotNull();
        assertThat(basket.getBasketRowList()).hasSize(1);
        assertThat(basket.getBasketRowList().get(0).getProduct()).isEqualTo(p2);
        assertThat(basket.getBasketRowList().get(0).getCount()).isEqualTo(3);
    }

    @Test
    @DisplayName("-1 count product")
    public void decCountProductById() {
        Product p2 = Product.builder()
                .id(2L)
                .name("Пирог 2")
                .description("Вкусный 2")
                .price(3.0)
                .priceSale(2.5)
                .build();

        basketService.incOrDecCountProductById(49L, 2L, false);

        BasketRow basketRow = basketRowRepository.findByBasket(basketService.getById(49L)).get(0);
        Basket basket = basketService.getById(49L);

        assertThat(basketRow).isNotNull();
        assertThat(basketRow.getId()).isEqualTo(94);
        assertThat(basket.getBasketRowList()).isNotNull();
        assertThat(basket.getBasketRowList()).hasSize(1);
        assertThat(basket.getBasketRowList().get(0).getProduct()).isEqualTo(p2);
        assertThat(basket.getBasketRowList().get(0).getCount()).isEqualTo(3);
    }

    @Test
    @DisplayName("delete product from basket")
    public void deleteProductById() {
        Product p1 = Product.builder()
                .id(1L)
                .name("Пирог")
                .description("Вкусный")
                .price(2.0)
                .priceSale(1.5)
                .build();

        basketService.deleteProductById(50L, 2L);

        BasketRow basketRow = basketRowRepository.findByBasket(basketService.getById(50L)).get(0);
        Basket basket = basketService.getById(50L);

        assertThat(basketRow).isNotNull();
        assertThat(basketRow.getId()).isEqualTo(92);
        assertThat(basket.getBasketRowList()).isNotNull();
        assertThat(basket.getBasketRowList()).hasSize(1);
        assertThat(basket.getBasketRowList().get(0).getProduct()).isEqualTo(p1);
        assertThat(basket.getBasketRowList().get(0).getCount()).isEqualTo(2);
    }

    @Test
    @DisplayName("delete all product from basket")
    public void clearById() {
        basketService.clearById(51L);

        List<BasketRow> basketRows = basketRowRepository.findByBasket(basketService.getById(51L));
        Basket basket = basketService.getById(51L);

        assertThat(basketRows).isNullOrEmpty();
        assertThat(basket.getBasketRowList()).isNullOrEmpty();
    }

    @Test
    @DisplayName("get basket by id")
    public void getById() {
        Product p1 = Product.builder()
                .id(1L)
                .name("Пирог")
                .description("Вкусный")
                .price(2.0)
                .priceSale(1.5)
                .build();

        Basket basket = basketService.getById(52L);

        assertThat(basket.getBasketRowList()).isNotNull();
        assertThat(basket.getBasketRowList()).hasSize(1);
        assertThat(basket.getBasketRowList().get(0).getId()).isEqualTo(89);
        assertThat(basket.getBasketRowList().get(0).getProduct()).isEqualTo(p1);
        assertThat(basket.getBasketRowList().get(0).getCount()).isEqualTo(3);
    }
}