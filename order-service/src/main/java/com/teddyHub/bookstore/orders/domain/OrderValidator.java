package com.teddyHub.bookstore.orders.domain;

import com.teddyHub.bookstore.orders.clients.catalog.Product;
import com.teddyHub.bookstore.orders.clients.catalog.ProductServiceClient;
import com.teddyHub.bookstore.orders.domain.models.CreateOrderRequest;
import com.teddyHub.bookstore.orders.domain.models.OrderItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class OrderValidator {
    private static final Logger log = LoggerFactory.getLogger(OrderValidator.class);

    private final ProductServiceClient client;

    public OrderValidator(ProductServiceClient client) {
        this.client = client;
    }

    void validate(CreateOrderRequest request) {
        Set<OrderItem> items = request.items();
        for (OrderItem item : items) {
            Product product = client.getProductByCode(item.code())
                    .orElseThrow(() -> new InvalidOrderException("Invalid Product code: " + item.code()));
            if (item.price().compareTo(product.price()) != 0) {
                log.error(
                        "Product price not matching. Actual price: {}, received price: {}",
                        product.price(),
                        item.price());
                throw new InvalidOrderException("Product price not matching");
            }
        }
    }
}
