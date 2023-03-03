package com.yabie.productcatalogue.controller;

import com.fasterxml.jackson.annotation.JsonProperty;

public record OfferRequest(@JsonProperty String offerName,
                           @JsonProperty String productName,
                           @JsonProperty Double discount,
                           @JsonProperty String validUntil,
                           @JsonProperty String couponCode,
                           @JsonProperty Double couponDiscount) {
}
