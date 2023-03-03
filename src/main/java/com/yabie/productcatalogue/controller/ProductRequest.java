package com.yabie.productcatalogue.controller;

import com.fasterxml.jackson.annotation.JsonProperty;

public final record ProductRequest(@JsonProperty String productName,
                                   @JsonProperty String productTypeName,
                                   @JsonProperty String organizationNo,
                                   @JsonProperty Double productPrice,
                                   @JsonProperty String productDescription,
                                   @JsonProperty Long productAmount) {
}
