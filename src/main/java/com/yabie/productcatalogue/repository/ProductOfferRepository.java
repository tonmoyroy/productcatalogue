package com.yabie.productcatalogue.repository;


import com.yabie.productcatalogue.model.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "offers", collectionResourceRel = "offers", itemResourceRel = "offer")
public interface ProductOfferRepository extends JpaRepository<Offer, Long> {
    Offer findByOfferName(String offerName);
}
