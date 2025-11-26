package com.danilo_alves.simplified_erp.application.gateway.stock;

import com.danilo_alves.simplified_erp.domain.entity.product.ProductDomain;
import com.danilo_alves.simplified_erp.domain.entity.stock.StockMovementDomain;

import java.util.List;
import java.util.Optional;

public interface StockGateway {
    Optional<ProductDomain> findProductById(Long id);
    StockMovementDomain  updateStock(ProductDomain product, StockMovementDomain movement);
    List<StockMovementDomain> getAll();
    StockMovementDomain getById(Long id);
}