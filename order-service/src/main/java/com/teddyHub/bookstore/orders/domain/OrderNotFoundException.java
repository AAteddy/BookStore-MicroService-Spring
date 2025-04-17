package com.teddyHub.bookstore.orders.domain;

public class OrderNotFoundException extends RuntimeException {
  public OrderNotFoundException(String message) {
    super(message);
  }
}
