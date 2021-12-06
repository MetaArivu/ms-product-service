package com.product.adapter.entities;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.product.adapter.dto.ProductEvent;

@Document(value = "product_details")
public class ProductDetails extends BaseEntity {

	@Indexed
	private String name;
	private String description;
	private String image;
	private Double price;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public static String invalidMsg() {
		return "Please enter valid Product data (LoginId/Name)";
	}

	@Override
	public String toString() {
		return id + "|" + name + "|" + description + " " + image + " " + price;
	}

	@JsonIgnore
	@Override
	public boolean isValid() {
		return true;
	}

	private ProductEvent productEvent(String type) {
		return new ProductEvent.Build()
				.description(description)
				.id(getId())
				.image(image)
				.name(name)
				.price(price)
				.type(type)
				.build();
	}
	
	public ProductEvent productCreatedEvent() {
		return new ProductEvent.Build()
				.description(description)
				.id(getId())
				.image(image)
				.name(name)
				.price(price)
				.type("CREATED")
				.build();
	}
	
	public ProductEvent productUpdateEvent() {
		return new ProductEvent.Build()
				.description(description)
				.id(getId())
				.image(image)
				.name(name)
				.price(price)
				.type("UPDATE")
				.build();
	}
}
