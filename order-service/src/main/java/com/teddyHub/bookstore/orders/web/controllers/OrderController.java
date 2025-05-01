package com.teddyHub.bookstore.orders.web.controllers;


import com.teddyHub.bookstore.orders.domain.OrderNotFoundException;
import com.teddyHub.bookstore.orders.domain.OrderService;
import com.teddyHub.bookstore.orders.domain.SecurityService;
import com.teddyHub.bookstore.orders.domain.models.CreateOrderRequest;
import com.teddyHub.bookstore.orders.domain.models.CreateOrderResponse;
import com.teddyHub.bookstore.orders.domain.models.OrderDTO;
import com.teddyHub.bookstore.orders.domain.models.OrderSummary;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private static final Logger log = LoggerFactory.getLogger(OrderController.class);

    private final OrderService orderService;
    private final SecurityService securityService;

    public OrderController(OrderService orderService, SecurityService securityService) {
        this.orderService = orderService;
        this.securityService = securityService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    CreateOrderResponse createOrder(@Valid @RequestBody CreateOrderRequest request) {
        String userName = securityService.getLoginUserName();
        log.info("Creating order for user: {}", userName);
        return orderService.createOrder(userName, request);
    }

    @GetMapping
    List<OrderSummary> getOrders() {
        String userName = securityService.getLoginUserName();
        log.info("Fetching orders for user: {}", userName);
        return orderService.findOrders(userName);
    }

    @GetMapping(value = "/{orderNumber}")
    OrderDTO getOrder(
            @PathVariable(value = "orderNumber") String orderNumber
    ) {

        log.info("Fetching order by id: {}", orderNumber);
        String userName = securityService.getLoginUserName();
        return orderService
                .findUserOrder(userName, orderNumber)
                .orElseThrow(() -> new OrderNotFoundException(orderNumber));
    }
}
