package dev.reactive.flux.app.shop.service;

import dev.reactive.flux.app.shop.dto.CreateShopDto;
import dev.reactive.flux.app.shop.model.Shop;
import dev.reactive.flux.app.shop.repository.ShopRepository;
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

    public Mono<Shop> getShop(final Integer id) {
        return shopRepository.findById(id);
    }

    public Mono<Shop> createShop(final CreateShopDto dto) {
        return shopRepository.save(dto.toShop());
    }
}
