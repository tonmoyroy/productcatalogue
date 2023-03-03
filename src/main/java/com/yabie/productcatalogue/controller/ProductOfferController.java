package com.yabie.productcatalogue.controller;

import com.yabie.productcatalogue.model.Offer;
import com.yabie.productcatalogue.model.Product;
import com.yabie.productcatalogue.repository.ProductOfferRepository;
import com.yabie.productcatalogue.repository.ProductRepository;
import com.yabie.productcatalogue.utils.DateConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

@RestController
@Slf4j
@RequestMapping("/v1/offer")
@RequiredArgsConstructor
public class ProductOfferController {
    private final ProductOfferRepository productOfferRepository;
    private final ProductRepository productRepository;

    @GetMapping("")
    public Collection<OfferRequest> getAllOffers() {
        Collection<OfferRequest> offerRequests = new ArrayList<>();
        productOfferRepository.findAll().stream().forEach(offer -> {
            offer.getProducts().stream().forEach(product -> {
                        OfferRequest offerRequest = new OfferRequest(
                                offer.getOfferName(),
                                product.getProductName(),
                                offer.getDiscount(),
                                offer.getValidUntil().toString(),
                                offer.getCouponCode(),
                                offer.getCouponDiscount()
                        );

                        offerRequests.add(offerRequest);
                    }
            );
        });

        return offerRequests;
    }

    @GetMapping("/products")
    public Collection<OfferProductsResponse> getAllOfferProducts() {
        Collection<OfferProductsResponse> offerProductsResponses = new ArrayList<>();
        productOfferRepository.findAll().stream().forEach(offer -> {
            offer.getProducts().stream().forEach(product -> {
                        OfferProductsResponse offerProductsResponse = new OfferProductsResponse(
                                product.getProductName(),
                                product.getProductPrice() - offer.getDiscount(),
                                offer.getDiscount(),
                                offer.getValidUntil().toString()
                        );

                        offerProductsResponses.add(offerProductsResponse);
                    }
            );
        });

        return offerProductsResponses;
    }

    @PostMapping("")
    public ResponseEntity createNewProduct(@RequestBody final OfferRequest offerRequest) {
        Product product = productRepository.findByProductName(offerRequest.productName());
        if (product == null) {
            log.warn("Product: " + offerRequest.productName() + " not found!");
            return ResponseEntity.badRequest().body("Product: " + offerRequest.productName() + " not found");
        }

        final Offer existingOffer = isExistingOffer(offerRequest);
        if (existingOffer != null) {
            updateOffer(existingOffer, offerRequest);

            return ResponseEntity.ok("Offer Updated");
        } else {
            final Offer newOffer = new Offer();
            newOffer.setOfferName(offerRequest.offerName());
            newOffer.setDiscount(offerRequest.discount());
            newOffer.setValidUntil(DateConverter.convertToDate(offerRequest.validUntil()));
            newOffer.setCouponCode(offerRequest.couponCode());
            newOffer.setCouponDiscount(offerRequest.couponDiscount());

            productOfferRepository.save(newOffer);

            newOffer.setProducts(Arrays.asList(product));
            productOfferRepository.save(newOffer);

            product.setOffer(newOffer);
            productRepository.save(product);

            return ResponseEntity.ok("Offer Created");
        }
    }

    private final Offer isExistingOffer(final OfferRequest offerRequest) {
        return productOfferRepository.findByOfferName(offerRequest.offerName());
    }


    //MAKING ASSUMPTIONS OF REQUIREMENTS
    private final void updateOffer(final Offer offer, final OfferRequest offerRequest) {

        for (Product product : offer.getProducts()) {
            if (!product.getProductName().equals(offerRequest.productName())) {
                offer.getProducts().add(product);
            }
        }

        LocalDateTime validUntil = DateConverter.convertToDate(offerRequest.validUntil());
        if (!offer.getValidUntil().equals(validUntil)) {
            offer.setValidUntil(validUntil);
        }

        if (!offer.getDiscount().equals(offerRequest.discount())) {
            offer.setDiscount(offerRequest.discount());
        }

        productOfferRepository.save(offer);
    }
}
