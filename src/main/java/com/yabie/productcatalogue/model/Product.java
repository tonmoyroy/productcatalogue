package com.yabie.productcatalogue.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "products")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@DiscriminatorValue("Products")
public class Product extends AbstractAuditableEntity implements Serializable {
    @Column
    private String productName;

    @ManyToOne(cascade = CascadeType.ALL)
    private ProductType productType;

    @Column
    private Double productPrice;

    @Column
    private String productDescription;

    @Column
    private Long productInStock;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Offer offer;

    @Column
    @Builder.Default
    private boolean active = true;

    @Override
    public String toString() {
        return this.productName + " + " +
                this.productPrice + " + " +
                this.productDescription + " + " +
                this.productInStock + " + " +
                this.active;
    }
}
