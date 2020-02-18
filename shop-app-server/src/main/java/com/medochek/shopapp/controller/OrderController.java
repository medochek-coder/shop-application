package com.medochek.shopapp.controller;

import com.medochek.shopapp.domain.Basket;
import com.medochek.shopapp.domain.ProductOrder;
import com.medochek.shopapp.dto.*;
import com.medochek.shopapp.service.BasketService;
import com.medochek.shopapp.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/order")
public class OrderController {
    @Autowired
    OrderService orderService;

    @Autowired
    BasketService basketService;

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getOrderById(@PathVariable(value = "id") Long orderId) {
        ProductOrder order = orderService.getById(orderId);
        if (order != null) {
            return new ResponseEntity<>(convert(order), HttpStatus.OK);
        } else {
            return getErrorResponseBody(ApplicationErrorTypes.ORDER_ID_NOT_FOUND);
        }
    }

    @RequestMapping(value = "/get/all", method = RequestMethod.GET)
    public ResponseEntity<?> getAllOrders() {
        List<ProductOrder> orders = orderService.getAll();
        return new ResponseEntity<>(convertList(orders), HttpStatus.OK);
    }

    @RequestMapping(value = "/get/inprogress", method = RequestMethod.GET)
    public ResponseEntity<?> getInProgressOrders() {
        List<ProductOrder> orders = orderService.getInProgressOrders();
        return new ResponseEntity<>(convertList(orders), HttpStatus.OK);
    }

    @RequestMapping(value = "/get/completed", method = RequestMethod.GET)
    public ResponseEntity<?> getCompletedOrders() {
        List<ProductOrder> orders = orderService.getCompletedOrders();
        return new ResponseEntity<>(convertList(orders), HttpStatus.OK);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<?> createOrder(@RequestBody OrderDTO orderDTO) {
        Basket basket  = basketService.getById(orderDTO.getBasket().getId());
        ProductOrder newOrder = orderService.create(new ProductOrder(orderDTO.getId(), orderDTO.getOwnerFirstName(),
                orderDTO.getOwnerLastName(), orderDTO.getOwnerPhone(), orderDTO.getOwnerEmail(),
                orderDTO.getStatus(), orderDTO.getDate(), basket));
        return new ResponseEntity<>(convert(newOrder), HttpStatus.OK);
    }

    @RequestMapping(value = "/complete/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> completeById(@PathVariable(value = "id") Long orderId) {
        ProductOrder order = orderService.completeById(orderId);
        return new ResponseEntity<>(convert(order), HttpStatus.OK);
    }
    private OrderDTO convert(ProductOrder dbModel) {
        return (dbModel == null) ? null : new OrderDTO(dbModel);
    }

    private ResponseEntity<ErrorResponseBody> getErrorResponseBody(ApplicationErrorTypes errorType) {
        return new ResponseEntity<>(new ErrorResponseBody(errorType), HttpStatus.NOT_FOUND);
    }

    private OrderListDTO convertList(List<ProductOrder> dbModel) {
        return (dbModel == null) ? null : new OrderListDTO(dbModel);
    }
}
