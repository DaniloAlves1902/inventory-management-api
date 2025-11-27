package com.danilo_alves.simplified_erp.domain.entity.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductDomain {
    private Long id;
    private Long version;
    private String sku;
    private String name;
    private String description;
    private Double price;
    private String currency;
    private Integer stock;

    public void creditStock(Integer quantity) {
        if (quantity <= 0) throw new IllegalArgumentException("Quantity must be positive");
        this.stock += quantity;
    }

    public void debitStock(Integer quantity) {
        if (quantity <= 0) throw new IllegalArgumentException("Quantity must be positive");
        if (this.stock < quantity) {
            throw new IllegalArgumentException("Insufficient stock for the product: " + this.name);
        }
        this.stock -= quantity;
    }

}
