package dev.reactive.flux.app.shop.controller;

import dev.reactive.flux.app.shop.model.Shop;
import dev.reactive.flux.app.shop.service.ShopService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@AllArgsConstructor
public class ShopController {

    private final ShopService shopService;

    @GetMapping("/shops")
    public Mono<List<Shop>> getShops() {
        return shopService.getShops();
    }
}
