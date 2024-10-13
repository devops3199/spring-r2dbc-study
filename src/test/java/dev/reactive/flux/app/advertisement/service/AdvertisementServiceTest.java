package dev.reactive.flux.app.advertisement.service;

import dev.reactive.flux.app.advertisement.model.Advertisement;
import dev.reactive.flux.app.advertisement.model.AdvertisementCategory;
import dev.reactive.flux.app.advertisement.model.AdvertisementStatus;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class AdvertisementServiceTest {

    @Test
    public void getAdvertisementsTest() {
        // Mock List
        var ad1 = Advertisement.builder()
                .id(1)
                .shopId(1)
                .name("Test1")
                .category(AdvertisementCategory.BANNER)
                .status(AdvertisementStatus.READY)
                .startedAt(LocalDateTime.of(2024, 9, 1, 7, 0, 0))
                .endedAt(LocalDateTime.of(2024, 9, 30, 7, 0, 0))
                .build();

        var ad2 = Advertisement.builder()
                .id(2)
                .shopId(2)
                .name("Test2")
                .category(AdvertisementCategory.LP_TOP)
                .status(AdvertisementStatus.READY)
                .startedAt(LocalDateTime.of(2024, 10, 1, 7, 0, 0))
                .endedAt(LocalDateTime.of(2024, 10, 31, 7, 0, 0))
                .build();

        var ads = List.of(ad1, ad2);

        // Mock Service
        AdvertisementService advertisementService = mock(AdvertisementService.class);
        when(advertisementService.getAdvertisements()).thenReturn(Mono.just(ads));

        var result = advertisementService.getAdvertisements();
        result.subscribe(data -> assertEquals(ads, data));

        verify(advertisementService, times(1)).getAdvertisements();
    }
}
