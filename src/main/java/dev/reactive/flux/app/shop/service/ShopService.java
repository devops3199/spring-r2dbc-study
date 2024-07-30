package dev.reactive.flux.app.shop.service;

import dev.reactive.flux.app.shop.dto.CreateShopDto;
import dev.reactive.flux.app.shop.dto.ModifyShopDto;
import dev.reactive.flux.app.shop.model.Shop;
import dev.reactive.flux.app.shop.repository.ShopRepository;
import dev.reactive.flux.common.error.APIException;
import dev.reactive.flux.common.error.ErrorMessage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class ShopService {

    private final ShopRepository shopRepository;

    public Mono<List<Shop>> getShops() {
        return shopRepository.findAll().collectList();
    }

    public Mono<Shop> getShopById(final Integer id) {
        return shopRepository.findById(id);
    }

    public Mono<Shop> createShop(final CreateShopDto dto) {
        return shopRepository.save(dto.toShop());
    }

    public Mono<Shop> modifyShop(final Integer id, final ModifyShopDto dto) {
        return shopRepository.findById(id)
            .switchIfEmpty(Mono.error(() -> new APIException(ErrorMessage.SHOP_NOT_FOUND)))
            .flatMap(shop -> {
                var modified = shop.patch(dto);
                return shopRepository.save(modified);
            });
    }
}
