package com.yabie.productcatalogue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class ProductcatalogueApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductcatalogueApplication.class, args);
	}
}
