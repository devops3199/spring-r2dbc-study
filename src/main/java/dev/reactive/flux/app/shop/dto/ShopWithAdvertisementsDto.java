package dev.reactive.flux.app.shop.dto;

import dev.reactive.flux.app.advertisement.model.AdvertisementCategory;
import dev.reactive.flux.app.advertisement.model.AdvertisementStatus;
import dev.reactive.flux.app.shop.model.ShopStatus;

import java.time.LocalDateTime;

public record ShopWithAdvertisementsDto(
        Integer shopId,
        String shopName,
        ShopStatus shopStatus,
        Integer advertisementId,
        String advertisementName,
        AdvertisementCategory advertisementCategory,
        AdvertisementStatus advertisementStatus,
        LocalDateTime advertisementStartedAt,
        LocalDateTime advertisementEndedAt
) {
}
