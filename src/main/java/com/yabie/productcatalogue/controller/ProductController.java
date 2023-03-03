package com.yabie.productcatalogue.controller;

import com.yabie.productcatalogue.model.Product;
import com.yabie.productcatalogue.model.ProductType;
import com.yabie.productcatalogue.repository.ProductRepository;
import com.yabie.productcatalogue.repository.ProductTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

@RestController
@RequestMapping("/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductRepository productRepository;
    private final ProductTypeRepository productTypeRepository;

    @GetMapping("")
    public Collection<ProductRequest> getAllProducts() {
        Collection<ProductRequest> productRequests = new ArrayList<>();
        productRepository.findAll().stream().forEach(product -> {
            ProductRequest productRequest = new ProductRequest(
                    product.getProductName(),
                    product.getProductType().getProductTypeName(),
                    product.getProductType().getOrganizationNo(),
                    product.getProductPrice(),
                    product.getProductDescription(),
                    product.getProductInStock()
            );
            productRequests.add(productRequest);
        });

        return productRequests;
    }

    @GetMapping("/productName")
    public ResponseEntity<ProductRequest> findProductByName(@RequestParam(name = "name", required = false) String productName) {
        Product product = null;
        if (productName != null) {
            product = productRepository.findByProductName(productName);
        }

        ProductRequest productRequest = null;
        if (product != null) {
            productRequest = new ProductRequest(
                    product.getProductName(),
                    product.getProductType().getProductTypeName(),
                    product.getProductType().getOrganizationNo(),
                    product.getProductPrice(),
                    product.getProductDescription(),
                    product.getProductInStock()
            );

        }

        return ResponseEntity.ok(productRequest);

    }

    @PostMapping("")
    public ResponseEntity createNewProduct(@RequestBody final ProductRequest productRequest) {
        Product existingProduct = isExistingProduct(productRequest);
        if (existingProduct != null) {
            updateProductInventory(existingProduct, productRequest);
            return ResponseEntity.ok("Product Updated");
        } else {
            final Product newProduct = new Product();

            ProductType productType = checkProductType(productRequest, newProduct);

            newProduct.setProductName(productRequest.productName());
            newProduct.setProductType(productType);
            newProduct.setProductDescription(productRequest.productDescription());
            newProduct.setProductPrice(productRequest.productPrice());
            newProduct.setProductInStock(productRequest.productAmount());

            productRepository.save(newProduct);

            return ResponseEntity.ok("Product Created");
        }
    }

    private final Product isExistingProduct(final ProductRequest productRequest) {
        return productRepository.findByProductName(productRequest.productName());
    }

    private final void updateProductInventory(Product product, ProductRequest productRequest) {
        final Long currentStock = product.getProductInStock();
        product.setProductInStock(currentStock + productRequest.productAmount());
        productRepository.save(product);
    }


    private final ProductType checkProductType(final ProductRequest productRequest, final Product newProduct) {
        ProductType productType = productTypeRepository.findByProductTypeName(productRequest.productTypeName());
        if (productType == null) {
            final ProductType newProductType = new ProductType();
            newProductType.setProductTypeName(productRequest.productTypeName());
            newProductType.setOrganizationNo(productRequest.organizationNo());
            newProductType.setProducts(Arrays.asList(newProduct));
            productTypeRepository.save(newProductType);

            return newProductType;
        }

        productType.getProducts().add(newProduct);
        return productType;
    }
}
