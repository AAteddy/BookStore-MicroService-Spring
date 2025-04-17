package com.teddyHub.bookstore.orders;

import org.springframework.boot.SpringApplication;

public class TestOrderServiceApplication {

	public static void main(String[] args) {
		SpringApplication
				.from(OrderServiceApplication::main)
				.with(TestContainersConfiguration.class)
				.run(args);
	}

}
