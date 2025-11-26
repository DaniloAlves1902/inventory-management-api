package com.danilo_alves.simplified_erp.infrastructure.decorator;

import com.danilo_alves.simplified_erp.application.usecase.stock.RegisterStockMovement;
import com.danilo_alves.simplified_erp.domain.entity.stock.StockMovementDomain;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
public class TransactionalRegisterStockMovement implements RegisterStockMovement {

    private final RegisterStockMovement decorated;

    @Override
    @Transactional
    public StockMovementDomain execute(StockMovementDomain input) {
        return decorated.execute(input);
    }
}
