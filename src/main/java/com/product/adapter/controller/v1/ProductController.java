package com.product.adapter.controller.v1;

import static com.product.APPConstant.V1;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product.adapter.dto.ProductDetailsDTO;
import com.product.adapter.dto.Response;
import com.product.adapter.entities.ProductDetails;
import com.product.domainlayer.service.ProductService;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping(V1)
public class ProductController {

	private static final Logger log = (Logger) LoggerFactory.getLogger(ProductController.class);

	@Autowired
	private ProductService svc;

 
	@PostMapping(value = "/")
	public Mono<ResponseEntity<Response<ProductDetails>>> save(@RequestBody ProductDetails _prod) {

		return svc.save(_prod).map(prod -> {
			return new ResponseEntity<Response<ProductDetails>>(
					new Response<ProductDetails>(true, "Record Saved Successully", prod), HttpStatus.OK);
		}).defaultIfEmpty(new ResponseEntity<Response<ProductDetails>>(
				new Response<ProductDetails>(false, "Record Not Saved Successully"), HttpStatus.NOT_FOUND));

	}
	
	@GetMapping(value = "/")
	public Mono<ResponseEntity<Response<List<ProductDetails>>>> allProducts() {

		return svc.allProducts().collectList()
				.map(list -> new ResponseEntity<Response<List<ProductDetails>>>(
						new Response<List<ProductDetails>>(true, "Record retrieved successully", list), HttpStatus.OK))
				.defaultIfEmpty(new ResponseEntity<Response<List<ProductDetails>>>(
						new Response<List<ProductDetails>>(false, "Record not found"), HttpStatus.NOT_FOUND));

	}
	
	@GetMapping(value = "/{id}")
	public Mono<ResponseEntity<Response<ProductDetailsDTO>>> productDetailsById(@PathVariable("id") String id) {

		return svc.findById(id)
				.map(prodDetails -> new ResponseEntity<Response<ProductDetailsDTO>>(
						new Response<ProductDetailsDTO>(true, "Record retrieved successully", prodDetails), HttpStatus.OK))
				.defaultIfEmpty(new ResponseEntity<Response<ProductDetailsDTO>>(
						new Response<ProductDetailsDTO>(false, "Record not found"), HttpStatus.NOT_FOUND));

	}
}
