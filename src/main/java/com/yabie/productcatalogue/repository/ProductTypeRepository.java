package com.yabie.productcatalogue.repository;

import com.yabie.productcatalogue.model.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "product_type", collectionResourceRel = "product_type", itemResourceRel = "product_type")
public interface ProductTypeRepository extends JpaRepository<ProductType, Long> {
    ProductType findByProductTypeName(String productTypeName);
}
