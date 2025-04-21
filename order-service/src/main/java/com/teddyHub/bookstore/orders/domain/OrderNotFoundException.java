package com.teddyHub.bookstore.orders.domain;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(String message) {
        super(message);
    }

    public static OrderNotFoundException forOrderNumber (String orderNumber) {
        return new OrderNotFoundException("Order with number " + orderNumber + " not found");
    }
}
