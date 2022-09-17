package com.vinsguru.productservice.dto;

import java.util.Objects;

public class ProductDto {

	private String id;
	private String description;
	private Integer price;

	public ProductDto() {
	}

	public ProductDto(String description, Integer price) {
		this.description = description;
		this.price = price;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	@Override
	public int hashCode() {
		return Objects.hash(description, id, price);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if ((obj == null) || (getClass() != obj.getClass()))
			return false;
		ProductDto other = (ProductDto) obj;
		return Objects.equals(description, other.description) && Objects.equals(id, other.id)
				&& Objects.equals(price, other.price);
	}

	@Override
	public String toString() {
		return "ProductDto [id=" + id + ", description=" + description + ", price=" + price + "]";
	}

}
