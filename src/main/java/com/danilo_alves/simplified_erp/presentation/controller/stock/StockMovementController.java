package com.danilo_alves.simplified_erp.presentation.controller.stock;

import com.danilo_alves.simplified_erp.application.gateway.user.UserGateway;
import com.danilo_alves.simplified_erp.application.usecase.stock.RegisterStockMovement;
import com.danilo_alves.simplified_erp.domain.entity.product.ProductDomain;
import com.danilo_alves.simplified_erp.domain.entity.stock.StockMovementDomain;
import com.danilo_alves.simplified_erp.domain.entity.user.UserDomain;
import com.danilo_alves.simplified_erp.presentation.dto.stock.StockMovementRequestDTO;
import com.danilo_alves.simplified_erp.presentation.dto.stock.StockMovementResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/movements")
@RequiredArgsConstructor
public class StockMovementController {

    private final UserGateway userGateway;
    private final RegisterStockMovement registerStockMovement;

    @PostMapping
    @PreAuthorize("hasAnyRole('HOST', 'ADMIN')")
    public ResponseEntity<StockMovementResponseDTO> create(@RequestBody StockMovementRequestDTO dto) {

        StockMovementDomain movement = new StockMovementDomain();
        movement.setQuantityChanged(dto.quantity());
        movement.setMovementTypeDomain(dto.movementType());

        ProductDomain product = new ProductDomain();
        product.setId(dto.productId());
        movement.setProduct(product);

        String username = getLoggedUsername();
        UserDomain user = userGateway.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
        movement.setChangedBy(user);

        StockMovementDomain savedMovement = registerStockMovement.execute(movement);

        StockMovementResponseDTO response = StockMovementResponseDTO.fromDomain(savedMovement);
        return ResponseEntity.created(URI.create("/api/v1/stocks/" + response.id())).body(response);
    }

    private String getLoggedUsername() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        }
        return principal.toString();
    }

}
