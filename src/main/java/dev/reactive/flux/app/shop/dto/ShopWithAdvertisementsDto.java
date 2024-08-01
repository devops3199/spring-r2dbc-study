package dev.reactive.flux.app.shop.dto;

import dev.reactive.flux.app.advertisement.model.AdvertisementCategory;
import dev.reactive.flux.app.advertisement.model.AdvertisementStatus;
import dev.reactive.flux.app.shop.model.ShopStatus;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ShopWithAdvertisementsDto(
        Integer shopId,
        String shopName,
        ShopStatus shopStatus,
        LocalDateTime shopCreatedAt,
        Integer advertisementId,
        String advertisementName,
        AdvertisementCategory advertisementCategory,
        AdvertisementStatus advertisementStatus,
        LocalDateTime advertisementStartedAt,
        LocalDateTime advertisementEndedAt
) {
}
