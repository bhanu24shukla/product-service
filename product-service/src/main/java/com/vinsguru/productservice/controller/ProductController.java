package com.vinsguru.productservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vinsguru.productservice.dto.ProductDto;
import com.vinsguru.productservice.service.ProductService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("product")
public class ProductController {

	@Autowired
	private ProductService service;

	@GetMapping("price-range")
	public Flux<ProductDto> getByPriceRange(@RequestParam("min") int min, @RequestParam("max") int max) {
		return this.service.getProductByPriceRange(min, max);
	}

	@GetMapping("all")
	public Flux<ProductDto> getAll() {
		return this.service.getALL();
	}

	@GetMapping("{id}")
	public Mono<ResponseEntity<ProductDto>> getProductById(@PathVariable String id) {
		return this.service.getProductById(id).map(ResponseEntity::ok)
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}

	@PostMapping("all")
	public Mono<ProductDto> createProduct(@RequestBody Mono<ProductDto> productDtoMono) {
		return this.service.createProduct(productDtoMono);
	}

	@PutMapping("{id}")
	public Mono<ResponseEntity<ProductDto>> updateProduct(@PathVariable String id,
			@RequestBody Mono<ProductDto> productDtoMono) {
		return this.service.updateProduct(id, productDtoMono).map(ResponseEntity::ok)
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}

	@DeleteMapping("{id}")
	public Mono<Void> deleteProduct(@PathVariable String id) {
		return this.service.deleteProduct(id);
	}

}
