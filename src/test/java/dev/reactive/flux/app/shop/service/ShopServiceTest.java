package dev.reactive.flux.app.shop.service;

import dev.reactive.flux.app.shop.model.Shop;
import dev.reactive.flux.app.shop.model.ShopStatus;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ShopServiceTest {

    @Test
    public void getShopsTest() {
        // Mock List
        var shop1 = Shop.builder()
                .id(1)
                .name("Test1")
                .status(ShopStatus.ACTIVE)
                .createdAt(LocalDateTime.of(2010, 1, 1, 7, 0, 0))
                .build();

        var shop2 = Shop.builder()
                .id(2)
                .name("Test2")
                .status(ShopStatus.INACTIVE)
                .createdAt(LocalDateTime.of(2010, 1, 1, 12, 0, 0))
                .build();

        var shops = List.of(shop1, shop2);

        // Mock Service
        ShopService shopService = mock(ShopService.class);
        when(shopService.getShops()).thenReturn(Mono.just(shops));

        var result = shopService.getShops();
        result.subscribe(data -> assertEquals(shops, data));

        verify(shopService, times(1)).getShops();
    }
}
