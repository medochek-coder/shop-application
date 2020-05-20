package com.medochek.shopapp.service.impl;

import com.medochek.shopapp.domain.Product;
import com.medochek.shopapp.repository.ProductRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("ProductService should ")
@ExtendWith(SpringExtension.class)
@Import(ProductServiceImpl.class)
@DataJpaTest
class ProductServiceImplTest {

    @Autowired
    private ProductServiceImpl productService;
    @Autowired
    private ProductRepository productRepository;

    @Test
    @DisplayName("create product")
    public void shouldCreateProduct() {
        Product p1 = Product.builder()
                .name("Пирог 3")
                .description("Вкусный 3")
                .price(1.0)
                .priceSale(0.5)
                .build();
        Product product = productService.createOrUpdate(p1);

        assertThat(product).isNotNull();
        assertThat(product.getId()).isNotNull();
        assertThat(product.getName()).isEqualTo("Пирог 3");
        assertThat(product.getDescription()).isEqualTo("Вкусный 3");
        assertThat(product.getPrice()).isEqualTo(1.0);
        assertThat(product.getPriceSale()).isEqualTo(0.5);
    }

    @Test
    @DisplayName("update product")
    public void shouldUpdateProduct() {
        Product p1 = Product.builder()
                .id(2L)
                .name("Пирог 3")
                .description("Вкусный 3")
                .price(1.0)
                .priceSale(0.5)
                .build();
        Product product = productService.createOrUpdate(p1);

        assertThat(product).isNotNull();
        assertThat(product.getId()).isEqualTo(2L);
        assertThat(product.getName()).isEqualTo("Пирог 3");
        assertThat(product.getDescription()).isEqualTo("Вкусный 3");
        assertThat(product.getPrice()).isEqualTo(1.0);
        assertThat(product.getPriceSale()).isEqualTo(0.5);
    }

    @Test
    @DisplayName("get all products with sale")
    public void getAllSaleProducts() {
        Product p1 = Product.builder()
                .id(1L)
                .name("Пирог")
                .description("Вкусный")
                .price(2.0)
                .priceSale(1.5)
                .build();
        Product p2 = Product.builder()
                .id(2L)
                .name("Пирог 2")
                .description("Вкусный 2")
                .price(3.0)
                .priceSale(2.5)
                .build();

        List<Product> allSale = productService.getAllSale();

        assertThat(allSale)
                .hasSize(2)
                .contains(p1, p2);

    }

    @Test
    @DisplayName("get all products without sale")
    public void getAllNoSaleProducts() {
        Product p = Product.builder()
                .id(3L)
                .name("Пирог 3")
                .description("Вкусный 3")
                .price(3.0)
                .build();

        List<Product> allNoSale = productService.getAllNoSale();

        assertThat(allNoSale)
                .hasSize(1)
                .contains(p);

    }

    @Test
    @DisplayName("get product by id")
    public void shouldGetProduct() {
        Product productById = productService.getById(1L);

        assertThat(productById).isNotNull();
        assertThat(productById.getId()).isEqualTo(1L);
        assertThat(productById.getName()).isEqualTo("Пирог");
        assertThat(productById.getDescription()).isEqualTo("Вкусный");
        assertThat(productById.getPrice()).isEqualTo(2.0);
        assertThat(productById.getPriceSale()).isEqualTo(1.5);
    }

    @Test
    @DisplayName("get all products")
    public void shouldGetAllProducts() {
        Product p1 = Product.builder()
                .id(1L)
                .name("Пирог")
                .description("Вкусный")
                .price(2.0)
                .priceSale(1.5)
                .build();
        Product p2 = Product.builder()
                .id(2L)
                .name("Пирог 2")
                .description("Вкусный 2")
                .price(3.0)
                .priceSale(2.5)
                .build();
        Product p3 = Product.builder()
                .id(3L)
                .name("Пирог 3")
                .description("Вкусный 3")
                .price(3.0)
                .build();
        List<Product> all = productService.getAll();

        assertThat(all)
                .hasSize(3)
                .contains(p1, p2, p3);
    }

    @Test
    @DisplayName("should delete by id")
    public void shouldDeleteById() {
        productService.deleteById(1L);

        assertThat(productService.getById(1L)).isNull();
    }
}