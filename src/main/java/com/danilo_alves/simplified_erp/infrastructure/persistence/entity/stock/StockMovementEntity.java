package com.danilo_alves.simplified_erp.infrastructure.persistence.entity.stock;

import com.danilo_alves.simplified_erp.infrastructure.persistence.entity.stock.enums.MovementEntity;
import com.danilo_alves.simplified_erp.infrastructure.persistence.entity.product.ProductEntity;
import com.danilo_alves.simplified_erp.infrastructure.persistence.entity.user.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;

@Entity
@Table(name = "stock_movements")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StockMovementEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    private Integer quantityChanged;

    private OffsetDateTime timeStamp;

    @ManyToOne(optional = false)
    @JoinColumn(name = "changed_by")
    private UserEntity changedBy;

    @Enumerated(EnumType.STRING)
    @Column(name = "movement_type")
    private MovementEntity movementTypeEntity;
}

