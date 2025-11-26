package com.danilo_alves.simplified_erp.domain.strategy;

import com.danilo_alves.simplified_erp.domain.entity.product.ProductDomain;

public interface StockMovementStrategy {
    void execute(ProductDomain product, Integer quantity);
}