package com.yabie.productcatalogue;

import com.yabie.productcatalogue.model.Product;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductTest {
    @Test
    public void testVehicle() {
        Product v = Product.builder().productName("test").build();
        v.setId(1L);
        assertThat(v.getId()).isEqualTo(1L);
        assertThat(v.getProductName()).isEqualTo("test");
    }
}
