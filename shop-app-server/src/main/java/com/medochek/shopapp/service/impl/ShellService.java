package com.medochek.shopapp.service.impl;

import com.medochek.shopapp.domain.Basket;
import com.medochek.shopapp.domain.ProductOrder;
import com.medochek.shopapp.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@ShellComponent
@Service
public class ShellService {
    @Autowired
    private ProductServiceImpl productService;

    @Autowired
    private BasketServiceImpl basketService;

    @Autowired
    private OrderServiceImpl orderService;

    @ShellMethod(value = "Create product command", key = {"cp", "createp"})
    public String create(@ShellOption String name, @ShellOption Double price, @ShellOption(defaultValue = "") String description,
                         @ShellOption(defaultValue = "") String image) {
        Product product = Product.builder()
                .name(name)
                .price(price)
                .description(description)
                .image(image)
                .build();
        product = productService.createOrUpdate(product);
        return "Created product: " + product;
    }

    @ShellMethod(value = "Update product command", key = {"up", "udatep"})
    public String update(@ShellOption Long id, @ShellOption String name, @ShellOption Double price, @ShellOption(defaultValue = "") String description,
                         @ShellOption(defaultValue = "") String image) {
        Product product = Product.builder()
                .id(id)
                .name(name)
                .price(price)
                .description(description)
                .image(image)
                .build();
        product = productService.createOrUpdate(product);
        return "Updated product: " + product;
    }

    @ShellMethod(value = "Get product by id command", key = {"gp", "getp"})
    public String getById(@ShellOption Long id) {
        Product product = productService.getById(id);
        if (product != null) {
            return "Found product: " + product;
        }
        return "Not found";
    }

    @ShellMethod(value = "Get all product command", key = {"gap", "getAllProduct"})
    public String getAll() {
        List<Product> products = productService.getAll();
        if (products != null) {
            return "Found product: " + products;
        }
        return "Not found";
    }

    @ShellMethod(value = "Delete product by id command", key = {"dp", "deletep"})
    public String deleteById(@ShellOption Long id) {
        productService.deleteById(id);
        return "Product deleted";
    }

    @ShellMethod(value = "Create empty basket command", key = {"cb", "createb"})
    public String createBasket() {
        Basket basket = basketService.createEmpty();
        return "Created basket: " + basket;
    }

    @ShellMethod(value = "Delete basket by id command", key = {"db", "deleteb"})
    public String deleteBasket(@ShellOption Long id) {
        basketService.deleteById(id);
        return "Basket deleted";
    }

    @ShellMethod(value = "Add product to basket command", key = {"addp", "addProductToBasket"})
    public String addProductInBasketById(@ShellOption Long idBasket, @ShellOption Long idProduct, @ShellOption(defaultValue = "1") Integer count) {
        basketService.addProductById(idBasket, idProduct, count);
        return "Product added to basket";
    }

    @ShellMethod(value = "Change count product in basket command", key = {"ccp", "changeCountProductInBasket"})
    public String changeCountProductInBasket(@ShellOption Long idBasket, @ShellOption Long idProduct, @ShellOption(defaultValue = "1") Integer count) {
        Integer result = basketService.changeCountProductById(idBasket, idProduct, count);
        if (result == 0) {
            return "Product removed from basket";
        }
        if (result > 0) {
            return "Product count in basket = " + result;
        }
        return "Product or basket does not exist";
    }

    @ShellMethod(value = "Delete product by id in basket command", key = {"dpib", "deleteProductInBasket"})
    public String deleteProductByIdInBasket(@ShellOption Long idBasket, @ShellOption Long idProduct) {
        basketService.deleteProductById(idBasket, idProduct);
        return "Product removed from basket";
    }

    @ShellMethod(value = "Clear basket by id command", key = {"clb", "clearBasket"})
    public String clearBasketById(@ShellOption Long idBasket) {
        basketService.clearById(idBasket);
        return "Basket cleared";
    }

    @ShellMethod(value = "Get basket by id command", key = {"getb", "getBasket"})
    public String getBasketById(@ShellOption Long idBasket) {
        Basket basket = basketService.getById(idBasket);
        if (basket != null) {
            return "Found basket: " + basket;
        }
        return "Not found";
    }

    @ShellMethod(value = "Get all baskets command", key = {"gab", "getAllBaskets"})
    public String getAllBasket() {
        List<Basket> baskets = basketService.getAll();
        if (baskets != null) {
            return "Found baskets: " + baskets;
        }
        return "Not found";
    }

    @ShellMethod(value = "Create order command", key = {"co", "createOrder"})
    public String createOrder(@ShellOption Long basketId, @ShellOption String ownerFirstName,
                              @ShellOption String ownerLastName, @ShellOption String ownerPhone,
                              @ShellOption String ownerEmail) {
        Basket basket = basketService.getById(basketId);
        if (basket == null) {
            return "Basket not found";
        }
        ProductOrder productOrder = ProductOrder.builder()
                                    .basket(basket)
                                    .ownerFirstName(ownerFirstName)
                                    .ownerLastName(ownerLastName)
                                    .ownerPhone(ownerPhone)
                                    .ownerEmail(ownerEmail)
                                    .date(LocalDateTime.now())
                                    .status("IN_PROGRESS")
                                    .build();

        //orderService.create(basketId, ownerFirstName, ownerLastName, ownerPhone, ownerEmail);
        return "Created order: " + orderService.create(productOrder);

    }

    @ShellMethod(value = "Get order by id command", key = {"geto", "getOrder"})
    public String getOrderById(@ShellOption Long idOrder) {
        ProductOrder productOrder = orderService.getById(idOrder);
        if (productOrder != null) {
            return "Found order: " + productOrder;
        }
        return "Not found";
    }

    @ShellMethod(value = "Get all orders in progress command", key = {"gao", "getAllOrders"})
    public String getAllOrders() {
        List<ProductOrder> productOrders = orderService.getAll();
        if (productOrders != null) {
            return "Found orders: " + productOrders;
        }
        return "Not found";
    }

    @ShellMethod(value = "Delete order by id command", key = {"do", "deleteOrder"})
    public String deleteOrder(@ShellOption Long id) {
        orderService.delete(id);
        return "Order deleted";
    }

    @ShellMethod(value = "Get all completed orders command", key = {"gaco"})
    public String getAllCompletedOrders(@ShellOption Long id) {
        List<ProductOrder> productOrders = orderService.getCompletedOrders();
        if (productOrders != null) {
            return "Found orders: " + productOrders;
        }
        return "Not found";
    }

    @ShellMethod(value = "Complete order by id command", key = {"compO"})
    public String completeOrder(@ShellOption Long id) {
        ProductOrder productOrder = orderService.getById(id);
        if (productOrder != null) {
            return "Order completed: " + orderService.complete(productOrder);
        }
        return "Not found";
    }
}

