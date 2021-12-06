package com.product.adapter.dto;

public class ProductCreatedEvent {

	private String id;
	private String name;
	private String description;
	private String image;
	private Double price;

	private ProductCreatedEvent(String id, String name, String description, String image, Double price) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.image = image;
		this.price = price;
	}

	public static class Build {

		private String id;
		private String name;
		private String description;
		private String image;
		private Double price;

		public Build id(String id) {
			this.id = id;
			return this;
		}

		public Build name(String name) {
			this.name = name;
			return this;
		}

		public Build description(String description) {
			this.description = description;
			return this;
		}

		public Build image(String image) {
			this.image = image;
			return this;
		}

		public Build price(Double price) {
			this.price = price;
			return this;
		}

		public ProductCreatedEvent build() {
			return new ProductCreatedEvent(id, name, description, image, price);
		}
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public String getImage() {
		return image;
	}

	public Double getPrice() {
		return price;
	}

}
