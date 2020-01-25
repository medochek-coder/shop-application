package com.medochek.shopapp.controller;

import com.medochek.shopapp.dto.ApplicationErrorTypes;
import com.medochek.shopapp.dto.BasketDTO;
import com.medochek.shopapp.dto.ErrorResponseBody;
import com.medochek.shopapp.service.BasketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.medochek.shopapp.domain.Basket;

@CrossOrigin
@RestController
@RequestMapping("api/basket")
public class BasketController {
    @Autowired
    private BasketService basketService;

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getBasketById(@PathVariable(value = "id") Long basketId) {
        Basket basket  = basketService.getById(basketId);
        if (basket != null) {
            return new ResponseEntity<>(convert(basket), HttpStatus.OK);
        } else {
            return getErrorResponseBody(ApplicationErrorTypes.BASKET_ID_NOT_FOUND);
        }
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<?> createBasket() {
        Basket newBasket = basketService.createEmpty();
        return new ResponseEntity<>(convert(newBasket), HttpStatus.OK);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteBasketById(@PathVariable(value = "id") Long basketId) {
        basketService.deleteById(basketId);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @RequestMapping(value = "/clear/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> clearById(@PathVariable(value = "id") Long basketId) {
        basketService.clearById(basketId);
        return getBasketById(basketId);
    }

    @RequestMapping(value = "/add/{idB}/{idP}", method = RequestMethod.POST)
    public ResponseEntity<?> addProductById(@PathVariable(value = "idB") Long basketId, @PathVariable(value = "idP") Long productId) {
        basketService.addProductById(basketId, productId);
        return getBasketById(basketId);
    }

    @RequestMapping(value = "/add/{idB}/{idP}/{c}", method = RequestMethod.POST)
    public ResponseEntity<?> addProductById(@PathVariable(value = "idB") Long basketId,
                                            @PathVariable(value = "idP") Long productId,
                                            @PathVariable(value = "c") Integer count) {
        basketService.addProductById(basketId, productId, count);
        return getBasketById(basketId);
    }

    @RequestMapping(value = "/change/{idB}/{idP}/{c}", method = RequestMethod.POST)
    public ResponseEntity<?> changeProductById(@PathVariable(value = "idB") Long basketId,
                                            @PathVariable(value = "idP") Long productId,
                                            @PathVariable(value = "c") Integer count) {
        basketService.changeCountProductById(basketId, productId, count);
        return getBasketById(basketId);
    }

    @RequestMapping(value = "/inc/{idB}/{idP}/{b}", method = RequestMethod.POST)
    public ResponseEntity<?> incOrDecCountProductById(@PathVariable(value = "idB") Long basketId,
                                               @PathVariable(value = "idP") Long productId,
                                               @PathVariable(value = "b") Boolean increase) {
        basketService.incOrDecCountProductById(basketId, productId, increase);
        return getBasketById(basketId);
    }

    @RequestMapping(value = "/delete/{idB}/{idP}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteProductById(@PathVariable(value = "idB") Long basketId, @PathVariable(value = "idP") Long productId) {
        basketService.deleteProductById(basketId, productId);
        return getBasketById(basketId);
    }
    private BasketDTO convert(Basket dbModel) {
        return (dbModel == null) ? null : new BasketDTO(dbModel);
    }

    private ResponseEntity<ErrorResponseBody> getErrorResponseBody(ApplicationErrorTypes errorType) {
        return new ResponseEntity<>(new ErrorResponseBody(errorType), HttpStatus.NOT_FOUND);
    }
}
