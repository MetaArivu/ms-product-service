package com.product.adapter.dto;

public class ProductDetailsDTO {

	private String id;
	private String name;
	private String description;
	private String image;
	private Double price;

	private ProductReviewDTO review;

	public ProductDetailsDTO(String id, String name, String description, String image, Double price,
			ProductReviewDTO review) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.image = image;
		this.price = price;
		this.review = review;
	}

	public static class Build {

		private String id;
		private String name;
		private String description;
		private String image;
		private Double price;

		private ProductReviewDTO review;

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

		public Build review(ProductReviewDTO review) {
			this.review = review;
			return this;
		}

		public ProductDetailsDTO build() {
			return new ProductDetailsDTO(id, name, description, image, price, review);
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

	public ProductReviewDTO getReview() {
		return review;
	}

}
