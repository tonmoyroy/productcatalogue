package com.yabie.productcatalogue.repository;


import com.yabie.productcatalogue.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "product", collectionResourceRel = "product", itemResourceRel = "products")
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findByProductName(String productName);
}
