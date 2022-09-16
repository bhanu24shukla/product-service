package com.vinsguru.productservice.service;

import com.vinsguru.productservice.dto.ProductDto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductService {

	Flux<ProductDto> getALL();

	Mono<ProductDto> getProductById(String id);

	Mono<ProductDto> createProduct(Mono<ProductDto> productDtoMono);

	Mono<ProductDto> updateProduct(String id, Mono<ProductDto> productDtoMono);

	Mono<Void> deleteProduct(String id);

	Flux<ProductDto> getProductByPriceRange(int min, int max);

}
