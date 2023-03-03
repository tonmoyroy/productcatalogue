package com.yabie.productcatalogue.controller;

import com.fasterxml.jackson.annotation.JsonProperty;

public record OfferProductsResponse(@JsonProperty String productName,
                                    @JsonProperty Double priceAfterDiscount,
                                    @JsonProperty Double totalDiscount,
                                    @JsonProperty String validUntil) {
}
