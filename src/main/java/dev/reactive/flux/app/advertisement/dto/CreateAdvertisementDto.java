package dev.reactive.flux.app.advertisement.dto;

import dev.reactive.flux.app.advertisement.model.Advertisement;
import dev.reactive.flux.app.advertisement.model.AdvertisementCategory;
import dev.reactive.flux.app.advertisement.model.AdvertisementStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record CreateAdvertisementDto(
        @NotBlank(message = "Shop ID 필수")
        Integer shopId,
        @NotBlank(message = "광고 이름 필수")
        String name,
        @NotNull(message = "광고 카테고리 필수")
        AdvertisementCategory category,
        @NotNull(message = "광고 상태 필수")
        AdvertisementStatus status,
        @NotNull(message = "광고 시작일")
        LocalDateTime startedAt,
        @NotNull(message = "광고 종료일")
        LocalDateTime endedAt
) {
    public Advertisement toAdvertisement() {
        return Advertisement.builder()
                .shopId(shopId)
                .name(name)
                .category(category)
                .status(status)
                .startedAt(startedAt)
                .endedAt(endedAt)
                .build();
    }
}
