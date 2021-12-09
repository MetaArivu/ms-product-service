package com.product.server;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

import com.product.domainlayer.service.ProductService;

import ch.qos.logback.classic.Logger;

@Configuration
public class AppEventListener {

	private static final Logger log = (Logger) LoggerFactory.getLogger(AppEventListener.class);
	  
	@Autowired
	private ProductService pdSvc;
	
	@EventListener(ApplicationReadyEvent.class)
	public void doSomethingAfterStartup() {
		log.info("Product Service Getting Started..");
		pdSvc.init();
	}

}