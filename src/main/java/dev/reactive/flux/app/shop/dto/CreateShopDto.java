package dev.reactive.flux.app.shop.dto;

import dev.reactive.flux.app.shop.model.Shop;
import dev.reactive.flux.app.shop.model.ShopStatus;

import java.time.LocalDateTime;

public record CreateShopDto(String name, ShopStatus status) {

    public Shop toShop() {
        return Shop.builder()
                .name(name)
                .status(status)
                .createdAt(LocalDateTime.now())
                .build();
    }
}
