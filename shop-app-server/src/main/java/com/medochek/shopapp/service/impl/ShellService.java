package com.medochek.shopapp.service.impl;

import com.medochek.shopapp.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.stereotype.Service;

import java.util.List;

@ShellComponent
@Service
public class ShellService {
    @Autowired
    private ProductServiceImpl productService;

    @ShellMethod(value = "Create product command", key = {"cp", "createp"})
    public String create(@ShellOption String name, @ShellOption Double price,@ShellOption(defaultValue = "") String description,
                         @ShellOption(defaultValue = "") String image)  {
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
    public String update(@ShellOption Long id, @ShellOption String name, @ShellOption Double price,@ShellOption(defaultValue = "") String description,
                         @ShellOption(defaultValue = "") String image)  {
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
    public String getById(@ShellOption Long id)  {
        Product product = productService.getById(id);
        if (product != null) {
            return "Found product: " + product;
        }
        return "Not found";
    }
    @ShellMethod(value = "Get all product command", key = {"ga", "getAll"})
    public String getAll()  {
        List<Product> products = productService.getAll();
        if (products != null) {
            return "Found product: " + products;
        }
        return "Not found";
    }

    @ShellMethod(value = "Delete product by id command", key = {"dp", "deletep"})
    public String deleteById(@ShellOption Long id)  {
        productService.deleteById(id);
        return "Deleted";
    }
}
