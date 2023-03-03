package com.yabie.productcatalogue.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "product_type")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@DiscriminatorValue("ProductType")
public class ProductType extends AbstractAuditableEntity implements Serializable {
    @NotEmpty
    private String productTypeName;

    @NotEmpty
    private String organizationNo;

    @OneToMany(mappedBy = "id", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Product> products;

    @Override
    public String toString() {
        return this.productTypeName + " + " +
                this.organizationNo;
    }
}
