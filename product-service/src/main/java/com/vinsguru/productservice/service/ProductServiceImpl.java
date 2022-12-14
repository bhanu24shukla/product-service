package com.vinsguru.productservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Range;
import org.springframework.stereotype.Service;

import com.vinsguru.productservice.dto.ProductDto;
import com.vinsguru.productservice.repository.ProductRepository;
import com.vinsguru.productservice.util.EntityDtoUtil;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository repository;

	@Override
	public Flux<ProductDto> getProductByPriceRange(int min, int max) {
		return this.repository.findByPriceBetween(Range.closed(min, max)).map(EntityDtoUtil::toDto);
	}

	@Override
	public Flux<ProductDto> getALL() {
		return this.repository.findAll().map(EntityDtoUtil::toDto);
	}

	@Override
	public Mono<ProductDto> getProductById(String id) {
		return this.repository.findById(id).map(EntityDtoUtil::toDto);
	}

	@Override
	public Mono<ProductDto> createProduct(Mono<ProductDto> productDtoMono) {
		return productDtoMono.map(EntityDtoUtil::toEntity).flatMap(this.repository::insert).map(EntityDtoUtil::toDto);
	}

	@Override
	public Mono<ProductDto> updateProduct(String id, Mono<ProductDto> productDtoMono) {
		return this.repository.findById(id)
				.flatMap(p -> productDtoMono.map(EntityDtoUtil::toEntity).doOnNext(e -> e.setId(id)))
				.flatMap(this.repository::save).map(EntityDtoUtil::toDto);
	}

	@Override
	public Mono<Void> deleteProduct(String id) {
		return this.repository.deleteById(id);
	}

}
