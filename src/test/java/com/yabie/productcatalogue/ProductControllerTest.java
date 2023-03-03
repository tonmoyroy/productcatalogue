package com.yabie.productcatalogue;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yabie.productcatalogue.controller.ProductController;
import com.yabie.productcatalogue.model.Product;
import com.yabie.productcatalogue.model.ProductType;
import com.yabie.productcatalogue.repository.ProductRepository;
import com.yabie.productcatalogue.repository.ProductTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(
        controllers = ProductController.class,
        excludeAutoConfiguration = {
                UserDetailsServiceAutoConfiguration.class, SecurityAutoConfiguration.class
        }
)
public class ProductControllerTest {

    @MockBean
    ProductRepository productRepository;

    @MockBean
    ProductTypeRepository productTypeRepository;

    @Autowired
    MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        given(this.productRepository.findById(1L))
                .willReturn(Optional.of(Product.builder().productName("Pepsi-1L").productType(ProductType.builder().productTypeName("Pepsi").build()).productPrice(20.0).build()));

        given(this.productRepository.findById(2L))
                .willReturn(Optional.empty());

        given(this.productRepository.save(any(Product.class)))
                .willReturn(Product.builder().productName("Pepsi-1L").productType(ProductType.builder().productTypeName("Pepsi").build()).productPrice(20.0).build());

        given(this.productRepository.findByProductName("Pepsi-1L"))
                .willReturn(Product.builder().productName("Pepsi-1L").productType(ProductType.builder().productTypeName("Pepsi").build()).productPrice(20.0).build());

        doNothing().when(this.productRepository).delete(any(Product.class));
    }

    @Test
    public void testFindByProductName() throws Exception {

        this.mockMvc
                .perform(
                        get("/v1/products/productName?name=Pepsi-1L")
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productName").value("Pepsi-1L"));

        verify(this.productRepository, times(1)).findByProductName("Pepsi-1L");
        verifyNoMoreInteractions(this.productRepository);
    }

}
