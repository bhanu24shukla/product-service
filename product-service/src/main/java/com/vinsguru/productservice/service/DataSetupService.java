package com.vinsguru.productservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import com.vinsguru.productservice.dto.ProductDto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class DataSetupService implements CommandLineRunner {

	@Autowired
	private ProductService productService;

	@Override
	public void run(String... args) throws Exception {
		ProductDto p1 = new ProductDto("4k-tv", 17000);
		ProductDto p2 = new ProductDto("slr-camera", 25000);
		ProductDto p3 = new ProductDto("samsung", 19000);
		ProductDto p4 = new ProductDto("headphone", 1300);

		Flux.just(p1, p2, p3, p4).flatMap(p -> this.productService.createProduct(Mono.just(p)))
				.subscribe(System.out::println);

	}

}
