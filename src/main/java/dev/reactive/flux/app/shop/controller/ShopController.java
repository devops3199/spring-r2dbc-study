package dev.reactive.flux.app.shop.controller;

import dev.reactive.flux.app.shop.dto.CreateShopDto;
import dev.reactive.flux.app.shop.model.Shop;
import dev.reactive.flux.app.shop.service.ShopService;
import dev.reactive.flux.common.dto.APIResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@AllArgsConstructor
public class ShopController {

    private final ShopService shopService;

    @GetMapping("/shops")
    public Mono<APIResponse<List<Shop>>> getShops() {
        return shopService.getShops()
                .map(shops -> APIResponse.<List<Shop>>builder()
                        .statusCode(200)
                        .message("success")
                        .data(shops)
                        .build());
    }

    @GetMapping("/shops/{id}")
    public Mono<APIResponse<Shop>> getShop(@PathVariable Integer id) {
        return shopService.getShop(id)
                .map(shops -> APIResponse.<Shop>builder()
                        .statusCode(200)
                        .message("success")
                        .data(shops)
                        .build());
    }

    @PostMapping("/shops")
    public Mono<APIResponse<Shop>> createShop(@Valid @RequestBody CreateShopDto createShopDto) {
        return shopService.createShop(createShopDto)
                .map(shop -> APIResponse.<Shop>builder()
                        .statusCode(201)
                        .message("success")
                        .data(shop)
                        .build());
    }
}
