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

    @ShellMethod(value = "Create product command, parameters: productName(string), price(double), " +
            "description(string, defaultValue = ''), imageLink(string, defaultValue = '')", key = {"p_cr"})
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

    @ShellMethod(value = "Update product command, parameters: productId(long), productName(string), price(double), " +
            "description(string, defaultValue = ''), imageLink(string, defaultValue = '')", key = {"p_upd",})
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

    @ShellMethod(value = "Get product by id command, parameters: productId(long)", key = {"p_g"})
    public String getById(@ShellOption Long id) {
        Product product = productService.getById(id);
        if (product != null) {
            return "Found product: " + product;
        }
        return "Not found";
    }

    @ShellMethod(value = "Get all product command", key = {"p_g_all"})
    public String getAll() {
        List<Product> products = productService.getAll();
        if (products != null) {
            return "Found product: " + products;
        }
        return "Not found";
    }

    @ShellMethod(value = "Delete product by id command, parameters: productId(long)", key = {"p_del"})
    public String deleteById(@ShellOption Long id) {
        productService.deleteById(id);
        return "Product deleted";
    }

    @ShellMethod(value = "Create empty basket command", key = {"b_cr"})
    public String createBasket() {
        Basket basket = basketService.createEmpty();
        return "Created basket: " + basket;
    }

    @ShellMethod(value = "Delete basket by id command, parameters: basketId(long)", key = {"b_del"})
    public String deleteBasket(@ShellOption Long id) {
        basketService.deleteById(id);
        return "Basket deleted";
    }

    @ShellMethod(value = "Add product to basket command, parameters: basketId(long), productId(long), " +
            "count(integer, defaultValue = 1)", key = {"b_add_p"})
    public String addProductInBasketById(@ShellOption Long idBasket, @ShellOption Long idProduct,
                                         @ShellOption(defaultValue = "1") Integer count) {
        basketService.addProductById(idBasket, idProduct, count);
        return "Product added to basket";
    }

    @ShellMethod(value = "Change count product in basket command, parameters: basketId(long), productId(long), " +
            "count(integer, defaultValue = 1)", key = {"b_ch_c_p", "changeCountProductInBasket"})
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

    @ShellMethod(value = "Increase or decrease count product in basket command, parameters: basketId(long), productId(long)," +
            "increase(boolean, defaultValue = false)", key = {"b_inc_p"})
    public String incOrDecCountProductById(@ShellOption Long idBasket, @ShellOption Long idProduct, @ShellOption boolean inc) {
        Integer result = basketService.incOrDecCountProductById(idBasket, idProduct, inc);
        if (result == 0) {
            return "Product removed from basket";
        }
        if (result > 0) {
            return "Product count in basket = " + result;
        }
        return "Product or basket does not exist";
    }


    @ShellMethod(value = "Delete product by id in basket command, parameters: basketId(long), productId(long)", key = {"b_del_p"})
    public String deleteProductByIdInBasket(@ShellOption Long idBasket, @ShellOption Long idProduct) {
        basketService.deleteProductById(idBasket, idProduct);
        return "Product removed from basket";
    }

    @ShellMethod(value = "Clear basket by id command", key = {"b_cl"})
    public String clearBasketById(@ShellOption Long idBasket) {
        basketService.clearById(idBasket);
        return "Basket cleared";
    }

    @ShellMethod(value = "Get basket by id command, parameters: basketId(long)", key = {"b_g"})
    public String getBasketById(@ShellOption Long idBasket) {
        Basket basket = basketService.getById(idBasket);
        if (basket != null) {
            return "Found basket: " + basket;
        }
        return "Not found";
    }

    @ShellMethod(value = "Get all baskets command", key = {"b_g_all"})
    public String getAllBasket() {
        List<Basket> baskets = basketService.getAll();
        if (baskets != null) {
            return "Found baskets: " + baskets;
        }
        return "Not found";
    }

    @ShellMethod(value = "Create order command, parameters: basketId(long), ownerFirstName(string), " +
            "ownerLastName(string), ownerPhone(string), ownerEmail(string)", key = {"o_cr"})
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
        return "Created order: " + orderService.create(productOrder);

    }

    @ShellMethod(value = "Get order by id command, parameters: orderId(long)", key = {"o_g"})
    public String getOrderById(@ShellOption Long idOrder) {
        ProductOrder productOrder = orderService.getById(idOrder);
        if (productOrder != null) {
            return "Found order: " + productOrder;
        }
        return "Not found";
    }

    @ShellMethod(value = "Get all orders in progress command", key = {"o_g_all"})
    public String getAllOrders() {
        List<ProductOrder> productOrders = orderService.getAll();
        if (productOrders != null) {
            return "Found orders: " + productOrders;
        }
        return "Not found";
    }

    @ShellMethod(value = "Delete order by id command, parameters: orderId(long)", key = {"o_del"})
    public String deleteOrder(@ShellOption Long id) {
        orderService.delete(id);
        return "Order deleted";
    }

    @ShellMethod(value = "Get all completed orders command", key = {"o_g_all_compl"})
    public String getAllCompletedOrders() {
        List<ProductOrder> productOrders = orderService.getCompletedOrders();
        if (productOrders != null) {
            return "Found orders: " + productOrders;
        }
        return "Not found";
    }

    @ShellMethod(value = "Complete order by id command, parameters: orderId(long)", key = {"o_compl"})
    public String completeOrder(@ShellOption Long id) {
        ProductOrder productOrder = orderService.getById(id);
        if (productOrder != null) {
            return "Order completed: " + orderService.complete(productOrder);
        }
        return "Not found";
    }
}

