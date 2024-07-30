package dev.reactive.flux.app.shop.dto;

import dev.reactive.flux.app.shop.model.ShopStatus;

public record ModifyShopDto(String name, ShopStatus status) {
}
