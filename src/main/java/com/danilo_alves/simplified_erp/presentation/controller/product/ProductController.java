package com.danilo_alves.simplified_erp.presentation.controller.product;

import com.danilo_alves.simplified_erp.application.usecase.product.*;
import com.danilo_alves.simplified_erp.domain.entity.product.ProductDomain;
import com.danilo_alves.simplified_erp.presentation.dto.product.ProductRequestDTO;
import com.danilo_alves.simplified_erp.presentation.dto.product.ProductResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final CreateProduct createProduct;
    private final UpdateProduct updateProduct;
    private final GetAllProduct getAllProducts;
    private final GetByIdProduct getByIdProduct;
    private final DeleteProduct deleteProduct;

    @PostMapping
    @PreAuthorize("hasAnyRole('HOST', 'ADMIN')")
    public ResponseEntity<ProductResponseDTO> create(@RequestBody ProductRequestDTO dto) {
        ProductDomain productToCreate = toDomain(dto);
        ProductDomain createdProduct = createProduct.execute(productToCreate);
        ProductResponseDTO response = ProductResponseDTO.fromDomain(createdProduct);
        return ResponseEntity.created(URI.create("/api/v1/products/" + response.id())).body(response);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('HOST', 'ADMIN')")
    public ResponseEntity<ProductResponseDTO> update(@PathVariable Long id, @RequestBody ProductRequestDTO dto) {
        ProductDomain productToUpdate = toDomain(dto);
        productToUpdate.setId(id);
        ProductDomain updatedProduct = updateProduct.execute(productToUpdate);
        ProductResponseDTO response = ProductResponseDTO.fromDomain(updatedProduct);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('HOST', 'ADMIN')")
    public ResponseEntity<List<ProductResponseDTO>> getAll() {
        List<ProductDomain> products = getAllProducts.execute(null);
        List<ProductResponseDTO> response = ProductResponseDTO.fromDomain(products);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('HOST', 'ADMIN')")
    public ResponseEntity<ProductResponseDTO> getById(@PathVariable Long id) {
        ProductDomain product = getByIdProduct.execute(id);
        ProductResponseDTO response = ProductResponseDTO.fromDomain(product);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        deleteProduct.execute(id);
        return ResponseEntity.noContent().build();
    }

    // Temporary method to convert DTO to Domain
    private ProductDomain toDomain(ProductRequestDTO dto) {
        return new ProductDomain(
                null,
                null,
                dto.sku(),
                dto.name(),
                dto.description(),
                dto.price(),
                dto.currency(),
                dto.stock()
        );
    }
}