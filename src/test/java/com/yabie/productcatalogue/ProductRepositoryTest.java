package com.yabie.productcatalogue;

import com.yabie.productcatalogue.model.Product;
import com.yabie.productcatalogue.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class ProductRepositoryTest {
    @Autowired
    private ProductRepository productRepository;

    @Test
    public void mapping() {
        Product saved = this.productRepository.save(Product.builder().productName("test").build());
        Product v = this.productRepository.getOne(saved.getId());
        assertThat(v.getProductName()).isEqualTo("test");
        assertThat(v.getId()).isNotNull();
        assertThat(v.getId()).isGreaterThan(0);
    }
}
