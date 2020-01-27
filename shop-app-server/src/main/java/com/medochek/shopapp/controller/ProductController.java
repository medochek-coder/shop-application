package com.medochek.shopapp.controller;

import com.medochek.shopapp.domain.Product;
import com.medochek.shopapp.dto.ApplicationErrorTypes;
import com.medochek.shopapp.dto.ErrorResponseBody;
import com.medochek.shopapp.dto.ProductDTO;
import com.medochek.shopapp.dto.ProductListDTO;
import com.medochek.shopapp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getProductById(@PathVariable(value = "id") Long productId) {
        Product product = productService.getById(productId);
        if (product != null) {
            return new ResponseEntity<>(convert(product), HttpStatus.OK);
        } else {
            return getErrorResponseBody(ApplicationErrorTypes.PRODUCT_ID_NOT_FOUND);
        }
    }

    @RequestMapping(value = "/get/all", method = RequestMethod.GET)
    public ResponseEntity<?> getAllProducts() {
        List<Product> products = productService.getAll();
        return new ResponseEntity<>(convertList(products), HttpStatus.OK);

    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<?> createOrUpdateProduct(@RequestBody ProductDTO productDTO) {
        Product newProduct = productService.createOrUpdate(new Product(productDTO.getId(), productDTO.getName(), productDTO.getDescription(),
                productDTO.getImage(), productDTO.getPrice(), productDTO.getPriceSale()));
        return new ResponseEntity<>(convert(newProduct), HttpStatus.OK);

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteProductById(@PathVariable(value = "id") Long productId) {
        productService.deleteById(productId);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    private ProductDTO convert(Product dbModel) {
        return (dbModel == null) ? null : new ProductDTO(dbModel);
    }

    private ProductListDTO convertList(List<Product> dbModel) {
        return (dbModel == null) ? null : new ProductListDTO(dbModel);
    }

    private ResponseEntity<ErrorResponseBody> getErrorResponseBody(ApplicationErrorTypes errorType) {
        return new ResponseEntity<>(new ErrorResponseBody(errorType), HttpStatus.NOT_FOUND);
    }
}
