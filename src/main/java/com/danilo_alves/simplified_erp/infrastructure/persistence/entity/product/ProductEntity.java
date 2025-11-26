package com.danilo_alves.simplified_erp.infrastructure.persistence.entity.product;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "products")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Version
    private Long version;

    @NotBlank
    private String sku;

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @NotNull
    private Double price;

    @NotNull
    private Integer stock;

    @NotBlank
    private String currency;

}

