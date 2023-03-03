package com.yabie.productcatalogue.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "offer")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@DiscriminatorValue("Offer")
public class Offer extends AbstractAuditableEntity implements Serializable {
    @Column
    private String offerName;

    @OneToMany(mappedBy = "id", cascade = CascadeType.PERSIST)
    private List<Product> products;

    @Column
    private Double discount;

    @Column
    private LocalDateTime validUntil;

    @Column
    private String couponCode;

    @Column
    private Double couponDiscount;

    @Override
    public String toString() {
        return this.offerName + " + " +
                this.discount + " + " +
                this.validUntil + " + " +
                this.couponCode + " + " +
                this.couponDiscount;
    }
}
