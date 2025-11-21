package com.danilo_alves.marketplace_inventory.infrastructure.mapper.product;

import com.danilo_alves.marketplace_inventory.domain.entity.product.ProductDomain;
import com.danilo_alves.marketplace_inventory.infrastructure.persistence.entity.product.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper responsável por converter objetos entre {@link ProductDomain}
 * e {@link ProductEntity}. Utiliza o MapStruct para gerar automaticamente
 * implementações de mapeamento, reduzindo boilerplate e garantindo
 * conversões consistentes.
 *
 * <p>Este mapper é registrado como componente Spring devido ao
 * {@code componentModel = "spring"}, permitindo sua injeção via {@code @Autowired}
 * ou construtor.</p>
 */
@Mapper(componentModel = "spring")
public interface ProductMapper {

    /**
     * Converte um objeto {@link ProductDomain} (camada de domínio)
     * para um {@link ProductEntity} (camada de persistência).
     *
     * <p>Campos específicos são mapeados diretamente:</p>
     * <ul>
     *     <li><strong>currencyId</strong> → <strong>currency</strong></li>
     *     <li><strong>listingTypeId</strong> → <strong>listingType</strong></li>
     * </ul>
     *
     * @param productDomain objeto da camada de domínio contendo dados do produto
     * @return instância de {@link ProductEntity} pronta para persistência
     */
    @Mapping(source = "currencyId", target = "currency")
    @Mapping(source = "listingTypeId", target = "listingType")
    ProductEntity toEntity(ProductDomain productDomain);

    /**
     * Converte um objeto {@link ProductEntity} (camada de persistência)
     * para um {@link ProductDomain} (camada de domínio).
     *
     * <p>Campos específicos são mapeados diretamente:</p>
     * <ul>
     *     <li><strong>currency</strong> → <strong>currencyId</strong></li>
     *     <li><strong>listingType</strong> → <strong>listingTypeId</strong></li>
     * </ul>
     *
     * @param productEntity entidade persistida do produto
     * @return instância de {@link ProductDomain} representando o modelo de domínio
     */
    @Mapping(source = "currency", target = "currencyId")
    @Mapping(source = "listingType", target = "listingTypeId")
    ProductDomain toDomain(ProductEntity productEntity);

}