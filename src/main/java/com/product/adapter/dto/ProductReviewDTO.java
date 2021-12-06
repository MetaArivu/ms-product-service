package com.product.adapter.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_NULL)
public class ProductReviewDTO {

	private boolean reviewFetched;
	private boolean reviewPresent;
	private List<Object> reviews;

	public ProductReviewDTO(boolean _reviewFetched,  List<Object> _reviews) {
		super();
		this.reviewFetched = _reviewFetched;
		this.reviewPresent = (_reviews!=null && _reviews.size()>0);
		this.reviews = _reviews;
	}

	public boolean isReviewFetched() {
		return reviewFetched;
	}

	public void setReviewFetched(boolean reviewFetched) {
		this.reviewFetched = reviewFetched;
	}

	public boolean isReviewPresent() {
		return reviewPresent;
	}

	public void setReviewPresent(boolean reviewPresent) {
		this.reviewPresent = reviewPresent;
	}

	public List<Object> getReviews() {
		return reviews;
	}

	public void setReviews(List<Object> reviews) {
		this.reviews = reviews;
	}

}
