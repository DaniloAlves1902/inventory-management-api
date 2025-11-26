package com.danilo_alves.simplified_erp.infrastructure.adapter.stock;

import com.danilo_alves.simplified_erp.application.gateway.stock.StockGateway;
import com.danilo_alves.simplified_erp.domain.entity.product.ProductDomain;
import com.danilo_alves.simplified_erp.domain.entity.stock.StockMovementDomain;
import com.danilo_alves.simplified_erp.domain.exception.stock.MovementNotFoundException;
import com.danilo_alves.simplified_erp.infrastructure.mapper.product.ProductMapper;
import com.danilo_alves.simplified_erp.infrastructure.mapper.stock.StockMovementMapper;
import com.danilo_alves.simplified_erp.infrastructure.persistence.repository.ProductRepositoryJPA;
import com.danilo_alves.simplified_erp.infrastructure.persistence.repository.StockMovementRepositoryJPA;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class StockMovementGatewayImpl implements StockGateway {

    private final ProductRepositoryJPA productRepository;
    private final StockMovementRepositoryJPA stockMovementRepository;
    private final ProductMapper productMapper;
    private final StockMovementMapper stockMovementMapper;

    @Override
    public Optional<ProductDomain> findProductById(Long id) {
        return productRepository.findById(id)
                .map(productMapper::toDomain);
    }

    @Override
    public StockMovementDomain updateStock(ProductDomain product, StockMovementDomain movement) {
        var productEntity = productMapper.toEntity(product);
        var savedProduct = productRepository.save(productEntity);

        var movementEntity = stockMovementMapper.toEntity(movement);
        movementEntity.setProduct(savedProduct);

        var savedMovementEntity = stockMovementRepository.save(movementEntity);
        return stockMovementMapper.toDomain(savedMovementEntity);
    }

    @Override
    public List<StockMovementDomain> getAll() {
        return stockMovementRepository.findAll().stream()
                .map(stockMovementMapper::toDomain)
                .toList();
    }

    @Override
    public StockMovementDomain getById(Long id) {
        return stockMovementRepository.findById(id)
                .map(stockMovementMapper::toDomain)
                .orElseThrow(() -> new MovementNotFoundException(id));
    }



}