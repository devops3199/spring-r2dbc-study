package dev.reactive.flux.app.shop.dto;

import dev.reactive.flux.app.shop.model.Shop;
import dev.reactive.flux.app.shop.model.ShopStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record CreateShopDto(
        @NotBlank(message = "Shop 이름 필수")
        String name,
        @NotNull(message = "Shop 상태 필수")
        ShopStatus status) {

    public Shop toShop() {
        return Shop.builder()
                .name(name)
                .status(status)
                .createdAt(LocalDateTime.now())
                .build();
    }
}
