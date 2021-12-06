package com.product.adapter.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.product.adapter.dto.ProductDetailsDTO;
import com.product.adapter.dto.ProductReviewDTO;
import com.product.adapter.dto.Response;
import com.product.adapter.entities.ProductDetails;
import com.product.adapter.repository.ProductRepository;
import com.product.domainlayer.service.ProductService;
import com.product.server.config.AppProperties;
import com.product.server.exceptions.DuplicateRecordException;
import com.product.server.exceptions.InvalidInputException;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductServiceImpl implements ProductService {

	private static final Logger log = (Logger) LoggerFactory.getLogger(ProductServiceImpl.class);

	@Autowired
	private ProductRepository prodRepo;

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private AppProperties appProp;
	
	
	@Override
	public Mono<ProductDetailsDTO> findById(String id) {
		log.info("Find Product By Id=" + id);

		if (id == null) {
			Mono<ProductDetailsDTO> fallback = Mono.error(new InvalidInputException("Invalid Id: " + id));
			return fallback;
		}
		return prodRepo.findByIdAndActive(id, true)
				.flatMap(prodDetails -> {
					ProductReviewDTO reviewDto = this.getProductReviews(prodDetails.getId());
					ProductDetailsDTO dto = new ProductDetailsDTO.Build()
							.description(prodDetails.getDescription())
							.id(prodDetails.getId())
							.image(prodDetails.getImage())
							.name(prodDetails.getName())
							.price(prodDetails.getPrice())
							.review(reviewDto)
							.build();
							
					
					return Mono.just(dto);
				});
	}

	@Override
	public Mono<ProductDetails> save(ProductDetails _prod) {

		log.info("Save Product Details=" + _prod);

		if (_prod == null || !_prod.isValid()) {
			Mono<ProductDetails> fallback = Mono.error(new InvalidInputException(ProductDetails.invalidMsg()));
			return fallback;
		}

		return prodRepo.save(_prod);
	}

	@Override
	public Mono<ProductDetails> update(String id, ProductDetails _prod) {

		log.info("Update Product Id=" + _prod.getId() + " Details=" + _prod);

		if (_prod == null || !_prod.isValid()) {
			Mono<ProductDetails> fallback = Mono.error(new InvalidInputException(ProductDetails.invalidMsg()));
			return fallback;
		}

		return prodRepo.findByIdAndActive(id, true).flatMap(prod -> {
			prod.setDescription(_prod.getDescription());
			prod.setImage(_prod.getImage());
			prod.setPrice(_prod.getPrice());
			prod.setName(_prod.getName());
			return prodRepo.save(prod);
		}).switchIfEmpty(Mono.error(new DuplicateRecordException("Record Not Found=" + _prod.getId())));

	}

	@Override
	public Flux<ProductDetails> allProducts() {
		log.info("Find all active products");
		return prodRepo.findByActive(true);
	}
//
//	private void publishEvent(UserCreatedEvent event) {
//		log.info("Publishing on topic={} data={}",KAFKA_TOPIC_USER_CREATED_EVENT,event);
//		ListenableFuture<SendResult<String, UserCreatedEvent>> listenableFuture = kafkaTemplate.send(KAFKA_TOPIC_USER_CREATED_EVENT, event.getId(), event);
//		
//		listenableFuture.addCallback(new ListenableFutureCallback<SendResult<String, UserCreatedEvent>>() {
//
//			@Override
//			public void onSuccess(SendResult<String, UserCreatedEvent> result) {
//				log.info("Ack Received, Message published successfully on topic={}, key={}",KAFKA_TOPIC_USER_CREATED_EVENT, result.getProducerRecord().key());
//
//			}
//
//			@Override
//			public void onFailure(Throwable ex) {
//				log.error("Message cannot be published Exception={}, Event={}, Topic={}", ex.getMessage(), event, KAFKA_TOPIC_USER_CREATED_EVENT);
//				log.error("Exception=",ex);
//			}
//		});;
//	}

	private ProductReviewDTO getProductReviews(String prodId) {
		String prodReviewUrl = appProp.getProdReviewURL(prodId);
		log.info("Fetch Product Reviews ProdId={}",prodId);
		log.info("Product Review URL={}",prodReviewUrl );
		String authHeader = MDC.get("Authorization");
		log.info("Authorization Key={}",authHeader);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", authHeader);
		HttpEntity httpEntity  = new HttpEntity<>(headers);
		
		ResponseEntity<Response> responseEntity = this.restTemplate.exchange(prodReviewUrl, HttpMethod.GET,httpEntity,Response.class);
		
		Response response = responseEntity.getBody();
		log.info("Product Review={}", response.toJSON());
		
		return new ProductReviewDTO(true, (List<Object>) response.getData());
	}
}
