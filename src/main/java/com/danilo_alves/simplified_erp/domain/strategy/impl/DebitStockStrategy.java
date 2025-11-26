package com.danilo_alves.simplified_erp.domain.strategy.impl;

import com.danilo_alves.simplified_erp.domain.entity.product.ProductDomain;
import com.danilo_alves.simplified_erp.domain.strategy.StockMovementStrategy;

public class DebitStockStrategy implements StockMovementStrategy {
    @Override
    public void execute(ProductDomain product, Integer quantity) {
        product.debitStock(quantity);
    }
}
